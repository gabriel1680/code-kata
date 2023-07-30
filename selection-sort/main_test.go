package main

import (
	"reflect"
	"testing"
)

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
        rng := []int{8, 9, 3, 4}
        expect := []int{3, 4, 8, 9}
        result := SelectionSort(rng)
        if !reflect.DeepEqual(expect, result) {
            t.Errorf("expected %v, recieved %v", expect, result)
        }
    })

    t.Run("should sort ascending order with multiple values", func(t *testing.T) {
        rng := []int{2, 0, 1, 4, 3, 8, 5, 7}
        expect := []int{0, 1, 2, 3, 4, 5, 7, 8}
        result := SelectionSort(rng)
        if !reflect.DeepEqual(expect, result) {
            t.Errorf("expected %v, recieved %v", expect, result)
        }
    })

}
