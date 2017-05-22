# Tieus Pizza

## Solution approach

Assumption: the cook can only make decisions based on which customers are currently waiting. She doesn't try to predict
which customers will show up soon and might be given priority in order to minimize average waiting time.

Given a fixed number of customers that are waiting, the minimum of average waiting time is achieved at the same point as
the minimum of total waiting time and depends on the serving order.

That minimum is achieved if customers are served in order of increased baking duration. Informal proof: observe that
waiting time has fixed and variable components. Fixed one is, for every customer, the duration of baking their own
pizza plus time they waited until the moment of decision making. Variable one is time waiting for other people's pizzas.
By placing short-baked pizzas earlier, less people have to wait for long-baked pizzas.

This makes for a straightforward solution: simulate the serving, building the list of currently waiting customers,
selecting one that has fastest-cooking pizza every time. In the end, calculate total waiting time and divide by nubmer
of customers.

## Caveat

To handle extreme cases, building a list of currently waiting customers must be done carefully. One such case is when
all of them show up at same time, which is maximal allowed, and order pizzas with maximal allowed duration.

To ensure high execution speed, list should be kept sorted. Picking the next customer means just taking the list head,
this is constant time when using linked list. 

## Running the code

You can use SBT like this:

    sbt "runMain TieusPizza src/test/resources/sample-input-00"

