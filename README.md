# Expert-System

## Table of content
* [Introduction](#introduction)
* [Description](#description)
* [Technologies](#technologies)
* [Setup](#setup)

## Introduction
A small application to which, we provide a knowledge base (fact base + rule base) and which enriches the fact base with other deductible facts using the rule base. 
An expert system must work in "forward chaining" and "backward chaining". We recall the general principle of the 2 types of chaining: 
### Front chaining : 
* Detect the rules whose premises are verified (filtering).
* Select the rule to apply.
* Apply the rule and deactivate it.
* Repeat until there are no more applicable rules. 
### Backward chaining:
* Initial goal placed at the top of a stack.
* Detection of rules that conclude at this goal.
* Conflict resolution.
* Application of the rule, i.e., the elements of the premises become new subgoals to be reached.
* Stop: empty stack or no applicable rule.


## Description
The work should be as follows. The rule base must be read from a file and modeled as follows: 
Rule(xi): IF (List of premises), THEN (List of actions)
* The application must give as output the set of rules used to prove a given fact n entrees, otherwise say that it is impossible.
* It must also give the new fact base after applying all the applicable rules of the system.
This project consist of 03 files:
* Main.java, the main class which contains uses the SYSTEM_EX.java class and shows the results.
* SYSTEM_EX.java, contains the main algorithm with 03 methods "getRules","ChainageAvant","ChainageArriere".
* text.txt, contains fact base and rule base.

## Technologies 
Project is created with:
* Java

## Setup
To run this project, you have to:
* Run Main.java (java Main.java).
* Enter fact .
* The application will shows the new fact base and the new rules else a message will apear means its impossible to demonstrate this fact.
