# SC2002 — Object-Oriented Programming Final Project

### 🧾 Overview
This repository contains our implementation of a HDB BTO app
---

### 🧩 UML Diagrams

- 📘 **Class Diagram:** [Google Drive](https://drive.google.com/file/d/15pSz6I1CwTE3Syg5YPZCTxLL35eUMB9z/view?usp=sharing)
- 📗 **Sequence Diagram:** [Google Drive](https://drive.google.com/file/d/1ZPB-S3n-ltpsRg66vPfY-xy4h-XSnRVn/view?usp=sharing)
- 📙 **Alternative Sequence (Lucidchart):** [Lucidchart](https://lucid.app/lucidchart/34800e0d-b5b7-46e3-824d-ea31c8394199/edit?viewport_loc=-30%2C41%2C1352%2C851%2C0_0&invitationId=inv_55a8077d-9c02-4029-95d7-76b96ddfd8c1)


**LOGIC FLOW** 
__________


1. Check role of user
2. Login ( with NRIC and password ), (using while loop check back with csv  file to see if user matches)
3. Extract from csv their, NRIC, name, marital status, password
4. Create current person object, (Using different constructors based on role)
5. Display options for execution


**CLASSES**
________

## 📦 `User` (Abstract Class)

Represents a base class for all users in the system.

### Attributes
- `String name`
- `int age`
- `String marital`
- `String password`
- `String nric`

### Methods

| Method Signature            | Description |
|----------------------------|-------------|
| `+ displayMenu(): void`    | **Abstract.** Subclasses must override this to display a role-specific menu. |

---

## 👤 `Applicant` (Subclass of `User`)

Represents a user who applies for projects.

### Special Notes
- Uses `nric` as a unique identifier.
- Uses `age` and `marital` status for eligibility checks.

### Methods

| Method Signature                                   | Description |
|---------------------------------------------------|-------------|
| `viewAvailableProjects(): void`                   | Filters and displays `Project` instances that are open (within `openingDate` and `closingDate`) and marked as visible. |
| `applyForProject(Project p): void`                | Creates an `Application` linking the applicant and the project, and submits it. |
| `viewAppliedProjects(): void`                     | Retrieves `Application` records linked to this applicant via their `nric`. |
| `requestApplicationWithdrawal(Application a): void` | Requests withdrawal from the given application. Updates status to `"Withdraw Requested"` or sends request to manager. |
| `createEnquiry(Enquiry e): void`                  | Creates a new `Enquiry`, referencing this applicant as the sender. |
| `viewEnquiries(): void`                           | Views all enquiries where the sender is this applicant. |
| `editEnquiry(Enquiry e): void`                    | Edits the enquiry if the sender is this applicant. |
| `deleteEnquiry(Enquiry e): void`                  | Deletes the enquiry if the sender is this applicant. |

---

## 🛂 `Officer` (Subclass of `User`)

Represents an officer who handles applications, enquiries, and project oversight.

### Methods

| Method Signature                                      | Description |
|------------------------------------------------------|-------------|
| `viewAndReplyEnquiry(): void`                        | Displays all unanswered enquiries and allows the officer to respond to them. |
| `generateReceipt(Applicant a, Project p): String`    | Creates a receipt containing project and applicant details. Can be returned as a plain string or a downloadable file (e.g. PDF). |
| `updateSelection(Applicant a, Project p): void`      | Updates the `Application` status to `"Selected"` or `"Not Selected"` based on selection results. |
| `registerProject(Project p): void`                   | Registers this officer to a project's `assignedOfficers` list if the officer slot limit allows. |

---

## 🧑‍💼 `Manager` (Subclass of `User`)

Represents the system manager responsible for creating and overseeing projects, approvals, and reporting.

### Methods

| Method Signature                                        | Description |
|--------------------------------------------------------|-------------|
| `createProject(Project p): void`                       | Adds a new project to the system’s project database. |
| `deleteProject(Project p): void`                       | Deletes or deactivates a project (e.g., marks as inactive). |
| `editProject(Project p): void`                         | Modifies any of the project's attributes, such as dates or unit counts. |
| `toggleProjectVisibility(Project p): void`             | Toggles the `isVisible` flag of the project to show/hide it from applicants. |
| `approveOrRejectApplication(Application a): void`      | Updates the application’s status to `"Approved"` or `"Rejected"`. |
| `approveOrRejectWithdrawal(Application a): void`       | Reviews a withdrawal request and sets the application status accordingly. |
| `approveOrRejectOfficerRegistration(Officer o): void`  | Approves or denies an officer’s request to join a project based on eligibility or slot availability. |
| `viewAllEnquiries(): void`                             | Views all user-submitted enquiries across the system. Useful for monitoring and issue resolution. |
| `generateReport(): String`                             | Generates a system report summarizing:<br>• Applications per project<br>• Approval rates<br>• Officer assignments<br>• Feedback from enquiries |

---

## 📄 `Application`

Represents an application submitted by an applicant for a specific project.

### Attributes
- `Project project` — The project the applicant applied for.
- `String flatType` — The flat type requested (e.g., `"2-Room"` or `"3-Room"`).
- `Applicant applicant` — Reference to the applicant who submitted this application.
- `String status` — One of `"Pending"`, `"Approved"`, `"Rejected"`, or `"Withdraw Requested"`.

### Usage
- Filter applications by user or project.
- Track and manage status updates for decision-making by officers or managers.

---

## 🏢 `Project`

Represents a housing project with details about flat types, availability, and assigned officers.

### Attributes
- `String type1`, `String type2` — Flat categories (e.g., `"2-Room"`, `"3-Room"`).
- `int type1Units`, `int type2Units` — Number of units available for each flat type.
- `double type1Price`, `double type2Price` — Price per unit for each flat type.
- `int officerSlots` — Number of officer slots available for this project.
- `List<String> assignedOfficers` — List of officer IDs or names assigned to the project.
- `String managerName` — Name of the manager overseeing the project.

### Usage
- Check unit and officer slot availability.
- Filter by location or availability.
- Control officer assignment and manager traceability.

---

## ❓ `Enquiry`

Represents a question or concern submitted by a user.

### Attributes
- `String message` — The content of the user’s question.
- `String response` — The reply from an officer or manager (can be empty if unanswered).
- `User sender` — Reference to the user who submitted the enquiry.

### Usage
- Track open and resolved issues.
- Maintain history of user support and system interaction.
- Allow response workflows and possible escalation.