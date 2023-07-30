package main

func SelectionSort(n []int) ([]int) {
    for i := 0; i < len(n); i++ {
        lower_index := i
        for j := i; j < len(n); j++ {
            if n[lower_index] > n[j] {
                lower_index = j
            }
        }
        swap(&n, lower_index, i)
    }
    return n
}

func swap(n *[]int, left int, right int) {
	aux := (*n)[left]
	(*n)[left] = (*n)[right]
    (*n)[right] = aux
}

