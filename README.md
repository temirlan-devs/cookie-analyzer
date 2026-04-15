# Cookie Analyzer

Command-line program to find the most active cookie(s) for a given day.

---

## Requirements

- Java 21 installed
- Maven installed

---

## Build

```bash
mvn clean package
```

---

## Run

Run the program with:

```bash
java -jar target/cookie-analyzer-1.0-SNAPSHOT.jar -f cookie_log.csv -d 2018-12-09
```

---

## Output

Prints the most active cookie(s) for the specified date, one per line.

Example:
```
AtY0laUfhglK3lC7
```

If multiple cookies have the same highest frequency, all are printed on separate lines.

---

## Assumptions

- Input file is sorted in descending order by timestamp
- Timestamp format follows ISO 8601 (e.g. 2018-12-09T14:19:00+00:00)
- Input is in UTC
- Entire file fits in memory

---

## Implementation Notes

- Uses a `HashMap` to count cookie occurrences
- Time complexity: O(n)
- Early termination is used thanks to sorted input

---

## Testing

Unit tests cover:

- Single most active cookie
- Multiple cookies (tie case)
- No results for a given date
- Invalid CSV format
- Missing file handling

Run tests with:

```bash
mvn test
```
