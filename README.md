implementing a simulator to demonstrate memory management  functions.
# Memory Management Simulator (Java)

This project simulates a memory system with:
- Translation Lookaside Buffer (TLB)
- Page Table
- Cache
- Main Memory

## Features
- Virtual to Physical address translation
- TLB with FIFO replacement
- Page Table with dynamic frame allocation
- Cache (direct mapped)
- Page fault handling

## Assumptions
- Virtual Address: 16 bits
- Page Size: 256 bytes
- Main Memory: 65,536 bytes (256 frames × 256 bytes)
- TLB: 16 entries
- Cache: 16 blocks, block size 16 bytes
- Replacement Policy: FIFO

## Requirements
- Java JDK 8 or later

## How to Run
cd memory-simulator/src
javac *.java
java Simulator
