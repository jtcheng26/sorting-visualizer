# sorting-visualizer
Simple sorting visualizations in Java.

##  Instructions:
1) Initialize an instance of SortingVisualizer with the number of frames per second as the parameter.
2) Make either an array of Bar objects or an array of integers using the makeRandomBars, makeRandomInts, or your own method of choice.
> OPTIONAL: Call setBars on the SortingVisualizer with the array as the parameter.
3) Call the sort method with either no parameters (if you did the optional step) or the array as the parameter.
> OPTIONAL: Call resetSort to do a different sort.  
> OPTIONAL: Call delay or secondsDelay methods to increase the time between sorts.   

Happy Sorting!

## Sorting Visualizer Methods
All are public instance methods.
| Method | Parameters | Return Type | Description |
| ------ | ---------- | ----------- | ----------- |
| `makeRandomBars` |`int length` | `Bar[]` | Makes an array of length Bar objects of various sizes to be used in the sort. |
| `makeBarsFromArray` | `int[] numList` | `Bar[]` | Makes an array of Bar objects from a given array of integers. |
| `setBars` | `Bar[] barList` | `void` | Eliminates the need to pass a parameter for the sorting methods. Also used to do multiple sorts on the same set of numbers; it will need to be called before each sort in that case. |
| `doBubbleSort` | `Bar[] numList` or None | `Bar[]` | Visualizes the Bubble Sort using the given Bar array. |
| `doSelectionSort` | `Bar[] numList` or None | `Bar[]` | Visualizes the Selection Sort using the given Bar array. |
| `doMergeSort` | `Bar[] numList` or None | `Bar[]` | Visualizes the Merge Sort using the given Bar array. |
| `doQuickSort` | `Bar[] numList` or None | `Bar[]` | Visualizes the Quick Sort using the given Bar array. |
| `secondsDelay` | `int seconds` | `void` | Adds a delay for the specified amount of seconds in between sorts so that the user can read the results. |

## Available Sorts
- Bubble Sort
- Selection Sort
- Merge Sort
- Quick Sort

## Examples
Demo files are included for the four sorting algorithms provided.  
