OrderProcessingSystem
Building a backend order processing system that reads orders from a file, processes them concurrently, updates inventory safely, logs results, and handles failures gracefully.

This project demonstrates Core Java concepts, multithreading, concurrency control, file handling, OOP principles, and exception handling in a clean and professional way.

Problem Statement

⦁	In a real-world order processing system
⦁	Multiple orders arrive at the same time
⦁	Inventory stock must never become negative
⦁	Orders must be processed concurrently for performance
⦁	Inventory updates must be thread-safe
⦁	Orders should fail gracefully if stock is insufficient

Challenges

⦁	Race conditions when multiple threads update stock
⦁	Negative inventory values
⦁	Inconsistent order results
⦁	Mapping file input to domain objects correctly

Solution Approach

1.	High-Level Flow
2.	Read orders from a file (Orders.txt)
3.	Convert file data into domain objects (Orders, Item)
4.	Process orders concurrently using a thread pool
5.	Synchronize inventory updates using ReentrantLock.
6.	Return success or failure result for each order

Problems Faced & Fixes
❌ Negative Inventory Values

Cause: Incorrect stock update logic
Fix: Validate before update + prevent stock going below zero

❌ Item Lookup Issues

Cause: Using new Item objects from file
Fix: Reuse inventory items via getItemByName()

❌ Race Conditions

Cause: Multiple threads updating shared map
Fix: Introduced ReentrantLock

🛠 Technologies Used

Java 8+

Core Java

Multithreading (ExecutorService, Callable, Future)

Concurrency (ReentrantLock)

File Handling

Collections Framework

📌 Key Interview Concepts Demonstrated

✔ Multithreading & Concurrency
✔ Executor Framework
✔ Thread Safety
✔ Race Condition Handling
✔ Object-Oriented Design
✔ Real-world problem modeling
✔ Exception Handling
✔ Clean Code Practices

🏁 Conclusion

This project demonstrates how real-world systems handle concurrent order processing while maintaining data consistency and correctness.

It focuses on clean design, correct synchronization, and professional coding practices expected in enterprise Java applications.
