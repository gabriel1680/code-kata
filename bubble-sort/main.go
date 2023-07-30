package main

func BubbleSort(n []int) []int {
    for i := 0; i < len(n) - 1; i++ {
        for j := 0; j < len(n) - i - 1; j++ {
            if n[j] > n[j + 1] {
                temp := n[j]
                n[j] = n[j + 1]
                n[j + 1] = temp
            }
        }
    }
    return n
}

