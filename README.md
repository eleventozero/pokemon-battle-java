# 🐉 Pokemon Battle (Java)
---
# MVP
✅ An object-oriented Pokémon battle system implemented in **Java** <br>
✅ The project follows a **classic OOP design**

---

The goal was to implement the same idea twice:
- once in a **functional** style - (Racket) https://github.com/eleventozero/pokemon-battle-racket
- once in an **object-oriented** style - (Java)

---

## ❗AI Assistance

AI tools were used to support project planning, code reviews, refactoring, and documentation. <br>
The final implementation, design decisions, and project structure were reviewed and validated by myself.

---

## Features

- Turn-based battle system (Player vs Enemy)
- Object-oriented state management (encapsulation & mutation)
- Type effectiveness system (Gen 1 inspired)
- SQLite database integration
- Modular architecture (Data, DB, Logic, UI)

---

## Architectur
- ![architectur.md](/docs/architecture.md)
- 

---

## Screenshots

### Main Menu
<img width="507" height="478" alt="grafik" src="https://github.com/user-attachments/assets/7380058d-ca53-4693-a7ba-c0cae8e1dd80" />

### Battle Screen
<img width="755" height="562" alt="grafik" src="https://github.com/user-attachments/assets/27ce660e-20c7-44fe-8b95-5d4766f09bf8" />

### Pokemon Selection
<img width="507" height="478" alt="grafik" src="https://github.com/user-attachments/assets/3430aa4d-b382-44f8-9b23-d022c3d1e5c1" />

---

## Data Model

### PokemonProfile

| Field        | Type   | Description |
|--------------|--------|-------------|
| name         | String | Name        |
| maxHp        | int    | Max HP      |
| attack       | int    | Attack      |
| defense      | int    | Defense     |
| type         | String | e.g. "fire" |
| attacks      | List   | attacks     |

---

### Pokemon

| Field     | Type   | Description   |
|-----------|--------|---------------|
| profile   | Object | base data     |
| currentHp | int    | current HP    |

---

### Attack

| Field  | Type   | Description |
|--------|--------|-------------|
| name   | String | Attack name |
| damage | int    | Base damage |
| type   | String | e.g. "water" |

---

### State (implicit)

| Field        | Description                  |
|--------------|------------------------------|
| player-team  | List of Pokemon              |
| enemy-team   | List of Pokemon              |
| p-active     | Player active index          |
| e-active     | Enemy active index           |
| turn         | controlled via GameFlow      |

---

## Future Improvements

- Status effects (poison, burn, etc.)
- Improved enemy AI (strategy instead of random)
- Extended type system (full Gen 1 mechanics)

---

## ⭐ Notes

This project is designed as a learning system to explore:

- Object-oriented programming in Java
- Translating functional design into OOP
- Clean architecture and separation of concerns
- Game logic implementation
- Database integration with SQLite
---
Any feedback, suggestions, or criticism are highly appreciated.
