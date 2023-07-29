package main

func SelectionSort(n []int) ([]int) {
    lower_index := 0
    for i := 1; i < len(n); i++ {
        if n[lower_index] > n[i] {
            swap(&n, lower_index, i)
        }
        lower_index = i
    }
    return n
}

func swap(n *[]int, left int, right int) {
	aux := (*n)[left]
	(*n)[left] = (*n)[right]
    (*n)[right] = aux
}

