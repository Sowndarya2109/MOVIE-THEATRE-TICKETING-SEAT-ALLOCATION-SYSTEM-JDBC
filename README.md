OVERVIEW:
A console-based Java application that manages movie shows, seat availability, and ticket bookings using JDBC and Oracle Database.
The system supports show management, seat booking, cancellation, and transaction safety with proper exception handling.

PROJECT STRUCTURE:
<img width="300" height="500" alt="image" src="https://github.com/user-attachments/assets/ff0d219a-dc60-466c-aee1-6c30c0e87c9c" />

FEATURES:
*View all movie shows
*Add and remove shows
*Book seats with availability check
*Cancel bookings
*Automatic seat update on booking/cancellation
*Transaction handling using JDBC commit & rollback
*Custom exception handling

FUNCTIONAL FLOW:
*Seat booking reduces available seats
*Cancellation restores seats
*Shows with active bookings cannot be deleted
*Transactions are rolled back on failure

HOW TO RUN:
1.Create required tables and sequences in Oracle DB
2.Update DB credentials in DBUtil.java
3.Run TheatreMain.java
4.Observe console output for booking and cancellation status

OUTPUT:
<img width="527" height="150" alt="image" src="https://github.com/user-attachments/assets/2625c034-dd48-4c02-8e36-54ca043ddbd0" />

