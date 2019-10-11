# print-numbers

### Problem - Print Numbers

Write some software using the technology of your choice that will transform numbers into English word equivalents.

Some examples:


| Sample input | Sample output |
|--------------|---------------|
| 0 | Zero |
| 13 | Thirteen |
| 85 | Eighty five |
| 5237 | Five thousand two hundred and thirty seven |


The input number should be a signed integer (32 bits)

### Solution description

The number is split into a binary tree following the rules inferred from the English language examples.
First the "multipliers" are detected. Differently than the decimal base, the English language uses more of a 1000 base. For example, we don't say "one million hundred thousand three ten thousand four thousand" for 1,134,000, but we say "one million one hundred and four thousand". 
For each multiplier further splitting is done like we would for an under-thousand number. This is done differently (from observation), using the hundreds and then shorthands for several numbers in the tens, that are traditionally used.

For every step of the splitting, done recursively, the tree is build on the left with more and more detail from the current multiplier, and on the right, with the splitting of the remainder, as it was a new number.

The stack size is not a concern, since it is pushed maximum twice for every digit of the number. The numbers have a maximum 10 digits.

###  	Implementation

We do not have a Java API for binary trees, so a simple one was implemented from scratch in the `NumberTreeNode` class.

The multipliers and units used to split the numbers are held ordered in `NumberDictionary`.

The values with their English name are held in the immutable `WordedNumber`. This class also holds the information about if it can be used with the preposition `and` or not.

I made the choice of letting the `Zero` and `Minus` cases out of the tree implementation. I considered it would have complicated the implementation un-neccessarily and hurt the maintenance. Instead, I chose to treat them right after input as a special processing.

