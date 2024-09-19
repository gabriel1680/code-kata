# Text Processor Kata

[Reference link](https://www.codurance.com/katas/text-processing)

## Introduction
As a developer that writes blog posts I want a tool that helps me to understand better the text I am writing. For that I need a way to know the following:

- What are the most common words used in the text?
- How many characters does the text have?

```java
interface Processor {
    void analyse(String text);
}
```

The usage of such interface is not required.

## First challenge
Given the following text:

`Hello, this is an example for you to practice. You should grab this text and make it as your test case.`

The output should be:

```shell
Those are the top 10 words used:

1. you
2. this
3. your
4. to
5. text
6. test
7. should
8. practice
9. make
10. it

The text has in total 21 words
```
### Some side notes:
As you may have noticed, the example assumes that You and you are the same, in other words, it's not case sensitive.
There is no order in which the words that have the same number are listed. For example, this and it, appear once, and they are not alphabetically ordered.
Next up, the kata starts to be a bit more complex. Make sure to complete this challenge first before going on into the second.