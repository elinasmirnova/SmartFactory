# OMO Smart Factory

Semestrální práce z předmětu B6B36OMO. 

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
