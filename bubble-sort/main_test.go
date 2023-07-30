package main

import "testing"

func TestBubbleSort(t *testing.T) {

    t.Run("should order the array", func (t *testing.T) {
        n := []int{4, 7, 3, 8, 1, 0}
        actual := BubbleSort(n)
        expected := []int{0, 1, 3, 4, 7, 8}
        for i := 0; i < len(actual); i++ {
            if (actual[i] != expected[i]) {
                t.Errorf("actual %d - expected %d", actual, expected)
                break
            }
        }
    })

}
