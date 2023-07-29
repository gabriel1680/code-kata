package main

import ("testing")

func TestSelectionSort(t *testing.T) {

    t.Run("should return the same array when it is empty", func(t *testing.T) {
        rng := []int{}
        expect := []int{}
        result := SelectionSort(rng)
        if (len(result) != len(expect)) {
            t.Errorf("expected %d, recieved %d", expect, result)
        }
    })

    t.Run("should sort ascending order", func(t *testing.T) {
        rng := []int{2, 1}
        expect := []int{1, 2}
        result := SelectionSort(rng)
        for i := 0; i < len(result); i++ {
          if result[i] != expect[i] {
                t.Errorf("expected %d, recieved %d", expect, result)
                break
            }
        }
    })

    t.Run("should sort ascending order with multiple values", func(t *testing.T) {
        rng := []int{2, 0, 1, 4, 3, 8, 5, 7}
        expect := []int{0, 1, 2, 9, 10, 5, 7, 8}
        result := SelectionSort(rng)
        for i := 0; i < len(result); i++ {
          if result[i] != expect[i] {
                t.Errorf("expected %d, recieved %d", expect, result)
                break
            }
        }
    })

}
