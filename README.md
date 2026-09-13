<div align="center">

# 💼 Employee Management System (EMS)

A desktop human resources management system built in **Java** utilizing **Swing (GUI)** and the **Model-View-Controller (MVC)** design pattern, featuring full CRUD operations and local state persistence via Java Object Serialization.

[![Java](https://img.shields.io/badge/Language-Java-ED8B00?logo=java&logoColor=white)](https://www.java.com/)
[![GUI: Swing](https://img.shields.io/badge/GUI-Java%20Swing-007396)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![Architecture: MVC](https://img.shields.io/badge/Architecture-MVC%20Pattern-green)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

</div>

---

## 📌 Architecture & Design Pattern

The application strictly separates concerns across three core architectural layers:

```text
┌────────────────────────────────────────────────────────┐
│                      VIEW LAYER                        │
│   • SystemGUI.java (Main JFrame, input forms & table)  │
│   • TableModel.java (Custom AbstractTableModel)        │
└───────────────────────────▲────────────────────────────┘
                            │ User Actions & UI Events
┌───────────────────────────▼────────────────────────────┐
│                   CONTROLLER LAYER                     │
│   • Manager.java (Business logic, validation, CRUD)    │
└───────────────────────────▲────────────────────────────┘
                            │ Reads & Updates State
┌───────────────────────────▼────────────────────────────┐
│                      MODEL LAYER                       │
│   • Employee.java (Data entity & Serializable POJO)    │
└────────────────────────────────────────────────────────┘
```

---

## 🌟 Key Features

* ➕ **Complete Employee CRUD Operations**:
  * **Create**: Register new employees with ID, name, designation, department, and salary.
  * **Read**: Interactive `JTable` rendering all employee records dynamically.
  * **Update**: Select and modify existing employee information in real-time.
  * **Delete**: Remove employee records with confirmation alerts.
* 💾 **Persistent Data Storage**: Automatic binary serialization and deserialization (`Serializable`) saving state across sessions without external database dependencies.
* 🛡️ **Input Validation**: Form checks guarding against duplicate IDs, empty fields, and invalid numerical formatting.

---

## 📂 Repository Structure

```text
Employee-Management-System/
├── SRC/
│   └── ems/
│       ├── model/
│       │   └── Employee.java        # Entity class encapsulating employee attributes
│       ├── controller/
│       │   └── Manager.java         # Controller managing the employee collection & I/O
│       └── view/
│           ├── SystemGUI.java       # Main graphical user interface
│           └── TableModel.java      # Custom JTable model binding
├── LICENSE                          # MIT License
└── README.md                        # Documentation
```

---

## 🚀 How to Compile & Run

### Prerequisites
* **Java Development Kit (JDK 8 or newer)** installed.

### Option 1: Command Line (Terminal)
```bash
# 1. Clone the repository
git clone https://github.com/mehtamanish9/Employee-Management-System.git
cd Employee-Management-System

# 2. Compile all source files into a 'bin' directory
javac -d bin SRC/ems/model/*.java SRC/ems/controller/*.java SRC/ems/view/*.java

# 3. Launch the application
java -cp bin ems.view.SystemGUI
```

### Option 2: IDE (IntelliJ IDEA / Eclipse / VS Code)
1. Open the project folder in your IDE.
2. Mark `SRC` as the **Sources Root**.
3. Locate `SRC/ems/view/SystemGUI.java` and click **Run**.

---

## 👨‍💻 Author

**Manish Mehta**  
* GitHub: [@mehtamanish9](https://github.com/mehtamanish9)  
* LinkedIn: [linkedin.com/in/manish-mehta04](https://www.linkedin.com/in/manish-mehta04)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

