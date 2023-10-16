import json
import os
from datetime import datetime

# Файл, в котором будут храниться заметки
notes_file = "notes.json"

# Проверка наличия файла и создание, если его нет
if not os.path.exists(notes_file):
    with open(notes_file, 'w') as f:
        json.dump([], f)

def load_notes():
    with open(notes_file, 'r') as f:
        notes = json.load(f)
    return notes

def save_notes(notes):
    with open(notes_file, 'w') as f:
        json.dump(notes, f)

def add_note():
    notes = load_notes()
    note = {
        "id": len(notes) + 1,
        "title": input("Введите заголовок заметки: "),
        "body": input("Введите текст заметки: "),
        "created_at": datetime.now().isoformat(),
        "last_updated_at": datetime.now().isoformat()
    }
    notes.append(note)
    save_notes(notes)
    print("Заметка добавлена успешно.")

def edit_note(note_id):
    notes = load_notes()
    for note in notes:
        if note["id"] == note_id:
            note["title"] = input("Введите новый заголовок заметки: ")
            note["body"] = input("Введите новый текст заметки: ")
            note["last_updated_at"] = datetime.now().isoformat()
            save_notes(notes)
            print("Заметка успешно отредактирована.")
            return
    print("Заметка с указанным ID не найдена.")

def delete_note(note_id):
    notes = load_notes()
    for note in notes:
        if note["id"] == note_id:
            notes.remove(note)
            save_notes(notes)
            print("Заметка успешно удалена.")
            return
    print("Заметка с указанным ID не найдена.")

def filter_notes_by_date():
    notes = load_notes()
    date_str = input("Введите дату для фильтрации (гггг-мм-дд): ")
    try:
        filter_date = datetime.strptime(date_str, "%Y-%m-%d").date()
    except ValueError:
        print("Неверный формат даты. Используйте гггг-мм-дд.")
        return

    filtered_notes = [note for note in notes if datetime.fromisoformat(note["created_at"]).date() == filter_date]
    if filtered_notes:
        print("Заметки, созданные в указанную дату:")
        for note in filtered_notes:
            print(f"ID: {note['id']}, Заголовок: {note['title']}, Создано: {note['created_at']}")
    else:
        print("Заметок, созданных в указанную дату, не найдено.")

def main():
    while True:
        print("\nМеню:")
        print("1. Добавить заметку")
        print("2. Редактировать заметку")
        print("3. Удалить заметку")
        print("4. Фильтр по дате")
        print("5. Выйти")

        choice = input("Выберите действие: ")

        if choice == "1":
            add_note()
        elif choice == "2":
            note_id = int(input("Введите ID заметки для редактирования: "))
            edit_note(note_id)
        elif choice == "3":
            note_id = int(input("Введите ID заметки для удаления: "))
            delete_note(note_id)
        elif choice == "4":
            filter_notes_by_date()
        elif choice == "5":
            print("Выход из программы.")
            break
        else:
            print("Неверный выбор. Попробуйте ещё раз.")

if __name__ == "__main__":
    main()
