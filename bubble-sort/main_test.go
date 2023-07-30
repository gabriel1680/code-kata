package main

import (
	"reflect"
	"testing"
)

func TestBubbleSort(t *testing.T) {

    t.Run("should order the array", func (t *testing.T) {
        n := []int{4, 7, 3, 8, 1, 0}
        expected := []int{0, 1, 3, 4, 7, 8}
        actual := BubbleSort(n)
        if !reflect.DeepEqual(actual, expected) {
            t.Errorf("actual %d - expected %d", actual, expected)
        }
    })

}
