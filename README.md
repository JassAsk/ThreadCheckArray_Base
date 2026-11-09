# ThreadCheckArray

## Overview

ThreadCheckArray is an educational Java project that solves the Subset Sum decision problem using recursive search and two worker threads.

Given a collection of integers and a target value `b`, the program checks whether a subset of those integers has a sum equal to `b`.

For example, for the input `1, 2, 3, 7` and target `5`, a valid solution is `2 + 3`. A Boolean solution array identifies the selected positions.

The exercise focuses on:
- Recursive algorithms and multithreading.
- Thread synchronization.
- Refactoring from `int[]` to `ArrayList<Integer>`.
- Javadoc documentation.
- Git collaboration and merge conflict resolution.

## Project Files

This README belongs in the project directory containing `src` and `.gitignore`.

| File | Purpose |
|---|---|
| `src/SharedData.java` | Stores the input collection, target value, and shared solution state. |
| `src/ThreadCheckArray.java` | Implements the worker logic for the recursive subset-sum search. |
| `src/TestThreadCheckArray.java` | Provides the console entry point, reads input, starts the workers, and displays the result. |
| `.gitignore` | Excludes generated files and local IDE settings from Git. |
| `docs/` | Contains generated HTML Javadoc documentation. |

## Recursive Subset Sum Algorithm

The recursive search considers two possibilities for each value:

1. Include the value in the subset and subtract it from the remaining target.
2. Exclude the value and continue with the same remaining target.

The search determines whether any combination reaches the target. A Boolean array records which input positions belong to the solution.

Two worker threads divide the initial search into two branches: one that includes the final input value and one that excludes it. Each worker continues recursively through its assigned branch.

The worst-case time complexity remains exponential, `O(2^n)`. Using two threads does not change this complexity.

## Thread Synchronization

The workers share a `SharedData` object containing:
- The input values.
- The target value.
- A flag indicating whether a solution has been found.
- The Boolean array representing the discovered solution.

Synchronization coordinates access to the shared solution state. The refactoring must preserve the existing `synchronized` blocks and the original threading behavior.

The console entry point waits for the workers using `join()` before displaying the final result.

## Refactoring to ArrayList<Integer>

The input collection named `array` is changed from `int[]` to `ArrayList<Integer>`.

The related fields, constructor parameters, return types, and operations are updated consistently.

| Operation | Before | After |
|---|---|---|
| Input collection type | `int[]` | `ArrayList<Integer>` |
| Read an element | `array[index]` | `array.get(index)` |
| Get the number of elements | `array.length` | `array.size()` |
| Append an input value | Assign to the next array position | `array.add(value)` |

Creating `new ArrayList<>(n)` sets the initial capacity; it does not create `n` elements. Input values must be added to the list.

All Boolean arrays, including `winArray`, remain `boolean[]`. Where their size depends on the input collection, they are initialized using:

```java
new boolean[array.size()]
```

The refactoring preserves the original algorithm and solution representation.

## Development Environment

The exercise uses:
- IntelliJ IDEA.
- BellSoft Liberica Full JDK 25 LTS.
- Git and GitHub.
- Continue connected to LM Studio.
- Qwen2.5-Coder-7B-Instruct.

AI-generated changes must be reviewed before they are accepted and committed.

## Running and Verifying the Program

After both students' changes have been merged:

1. Open the project in IntelliJ IDEA.
2. Verify that the required JDK is configured.
3. Open `TestThreadCheckArray.java`.
4. Run its `main()` method.

Use the verification input specified in the exercise:

```text
4
1 2 3 7
5
```

The expected solution selects the values `2` and `3`:

```text
Solution for b : 5, n = 4
C: 0 1 1 0
```

Spacing and additional output lines may vary.

Both students run this verification after synchronizing their local projects.

## Javadoc Documentation

Javadoc describes the classes, fields, constructors, and methods, including relevant parameters and return values.

Generic types inside Javadoc comments are formatted safely:

```java
{@code ArrayList<Integer>}
```

This prevents angle brackets from being interpreted as HTML tags.

To generate the documentation in IntelliJ IDEA:

1. Select **Tools → Generate JavaDoc**.
2. Select **Whole project**.
3. Set the output directory to `docs` inside the project directory.
4. Generate the documentation.
5. Open `docs/index.html` in a browser.

Although `docs/` is excluded from Git by the exercise's `.gitignore`, it must be included in the final submission ZIP.

## Authors and Collaboration Roles

The base project was supplied as part of the course. Existing author credits in the supplied source files are retained.

| Student | Name | Responsibilities |
|---|---|---|
| A | Yasmin Askarov | Sets up the repository, invites B, refactors `SharedData.java` and `ThreadCheckArray.java`, and creates this README. |
| B | Lihi Elias | Clones the repository, creates a feature branch, refactors `TestThreadCheckArray.java` and `ThreadCheckArray.java`, and performs the merge and conflict resolution. |

### Branch Names

In this repository, `master` is used as the main development branch in place of the `main` branch named in the exercise instructions.

Student B works on a separate feature branch created from `master`.

### Collaboration Workflow

1. Student A commits and pushes the initial project to `master`.
2. Student B clones the repository and creates a feature branch from `master`.
3. Both students perform their assigned refactoring.
4. Each student commits and pushes their changes to the appropriate branch.
5. Student A creates and pushes the README to `master`.
6. Student B switches to `master` and pulls Student A's updates.
7. Student B merges the feature branch into `master`.
8. Any conflicts in `ThreadCheckArray.java` are resolved using IntelliJ IDEA's visual 3-Way Merge tool.
9. Student B verifies the merged program and pushes the result.
10. Student A pulls the merged result and runs the verification again.

During conflict resolution, the final code must preserve the required collection operations, Boolean arrays, synchronization, and valid Javadoc.

The Git Log graph is used to inspect the branch history and merge. Git Blame is used to inspect line attribution.

## License

License designation required by the exercise: **Braude College Academic License**.

Refer to the course-provided license text or the instructor for the applicable terms. This README does not define additional permissions or restrictions.

## Submission

Submit one ZIP file named:

```text
G<GroupNum>_Ex2.zip
```

For example:

```text
G05_Ex2.zip
```

The ZIP must contain:
- The complete project folder and Java source code.
- The hidden `.git` directory containing the repository history.
- The generated `docs/` folder.
- The completed `ex2_submission_template.docx`, including team details, the GitHub URL, required screenshots, and reflection answers.