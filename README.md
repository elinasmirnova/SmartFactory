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
 

Eventy a oprava: jak to funguje?

1. Stav stroje se mění z NORMAL do BROKEN
2. Posílá se event BREAKDOWN
3. Event handler přidává event BREAKDOWN do eventQueue
4. Event handler bere event z eventListu a přidává rozbitý stroj do machineQueue
5. Repair handler bere rozbitý stroj z machineQueue, volá dostupného opraváře a začíná opravu
6. Stav stroje se mění z BROKEN do UNDER REPAIR
7. Posílá se event START REPAIR
8. Event handler dostává START REPAIR event 
9. Po několika taktech (počet taktů pro opravu stroje) se stroj opraví (health = 100)
10. Stav stroje se mění z UNDER REPAIR do NORMAL
11. Event handler dostává FINISH REPAIR event 

