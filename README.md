# OMO Smart Factory

## Semestrální práce z předmětu B6B36OMO. 

System simuluje práci továrny na nábytek. 

Továrnu jsme postavili pomocí vzoru Builder, který pomáhá vytvářet složité objekty krok za krokem. Továrna má výrobní linky na kterých probíhá práce strojů a lidí. V naší továrně máme tři linky. Každá linka je určena k výrobě jednoho druhu produktu. Mezi produkty továrny patří židle, stůl a skříň. 

Každý typ stroje má svou spotřebu a náklady na provoz, ale také opotřebení které se s časem zvyšuje. Pokud se stroj rozbije, automaticky se spustí událost “porucha” a stroj bude přidán do fronty rozbitých strojů, čekajících na opraváře. Pro znovupoužití opravářů je realizován vzor Object Pool.

Stroje, lidi a produkty navštěvuje ředitel a inspektor. U těchto tříd je důležité mít dostupnou vždy jen jednu a tu samou instanci, proto používáme vzor Singleton.  Během návštěvy se názvy provedených akcí zapisují do logu.

### Pro implementaci továrny jsme použili 7 design patternů:
* **Builder** - 
* **Strategy** - 
* **Object pool** - tento pattern jsme použili ve třídě RepairStatus. Třída poskytuje instance třídy Repairman a obsahuje 2 pole: available, kde jsou už nainstancovaní opraváři, a working repairmen. Když bude zavolána metoda getRepairman(), třída vezme opraváře z pole available (pokud pole není prázdné)  a vloží ho do pole working.
* **Singleton** - tento pattern se vyskytuje v několika třídách: RepairStatus, Queue, Manager, Inspector. U těchto tříd je důležité mít dostupnou vždy jen jednu a tu samou instanci.
* **Factory**
* **Visitor**
* **Observer**
 

REPAIR AND EVENTS
how does it work?

1. Machine's state changes from NORMAL to BROKEN
2. Machine adds event BREAKDOWN to eventList
3. Event handler gets the event from eventList and adds the broken machine from the event to the machineQueue
4. Repair handler gets the broken machine from the machineQueue, calls for an available repairman and starts the repair process
5. Machine's state changes from BROKEN  to UNDER REPAIR
5. When the state changes, an event START REPAIR is called
6. Event handler gets the START REPAIR event 
7. After several ticks (the number of ticks needed to fix the machine) fixes the machine (health = 100)
8. Machine's state changes from UNDER REPAIR to NORMAL
9. Event handler gets the FINISH REPAIR event 

