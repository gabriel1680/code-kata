package main

import (
	"reflect"
	"testing"
)

func TestInsertionSort(t *testing.T) {

    t.Run("should sort the given array", func (t *testing.T) {
        arrange := []int{8, 9, 0, 1}
        expected := []int{0, 1, 8, 9}
        actual := InsertionSort(arrange)
        if !reflect.DeepEqual(actual, expected) {
            t.Errorf("expected %v - actual %v", expected, actual)
        }
    })

}
