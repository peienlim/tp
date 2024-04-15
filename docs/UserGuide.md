---
layout: default.md
title: "User Guide"
pageNav: 3
---

# EventBook User Guide

## Welcome to EventBook!

Thank you for downloading EventBook as your project management software of choice!

Choose a topic from the table of contents below / or in the sidebar to find answers, or step-by-step
guides on how to use EventBook.
<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Product Overview

EventBook is a **desktop application for Student Leaders to manage the contacts from different events, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, EventBook can get your contact management tasks done faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------

## Using This User Guide

This user guide provides in-depth summaries of all the commands available to you. To make it easier to find the
information you need, you can either:

1. Refer to the Table of Contents above
2. Search within the guide with the following steps:
    1. Press Ctrl + F (Windows OS) or Cmd + F (MacOS)
    2. A search box or dialogue should appear on your screen
    3. Type the keyword or phrase that you want to find into the search box and press enter

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Quick Start

Before we can begin to use EventBook, we advise you to follow the simple steps below to ensure that EventBook is set up
correctly.

1. Ensure you have Java `11` or above installed in your Computer.
    * MacOS: [link](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html)
    * Windows
      OS: [link](https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx#zulu)

2. Download the latest `EventBook.jar` from [here](https://github.com/AY2324S2-CS2103T-T11-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your EventBook.

4. Open a command terminal (`Terminal` for MacOS, `Windows Terminal` for Windows OS)
    * MacOS
        1. Right-click your _home folder_
        2. Left click on `Services`
        3. Select `New Terminal at Folder`
    * Windows OS
        1. Using `Windows Explorer`, navigate to your _home folder_
        2. Right click anywhere in the folder
        3. Left click on `Open in Windows Terminal`

5. Use the `java -jar EventBook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data. <br>
   ![Ui](images/Ui.png)

6. Type a command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open
   the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.
   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the EventBook.
   * `assign 1 t/E-event1` : Assigns the 1st contact shown in the current list with the event tag named `event1`.
   * `assign John t/tag1` : Assigns the contact named John in the current list with the tag named `tag1`.
   * `clear` : Deletes all contacts.
   * `ctag Friend` : Creates a tag name `Friend`.
   * `ctag t/E-orientation dc/Orientation! sd/2024-04-04 02:02:02 ed/2024-04-05 02:02:02` : Creates an event tag.
   * `dtag Friend` : Deletes a tag named `Friend`.
   * `delete 3` : Deletes the 3rd contact shown in the current list.
   * `devent orientation` : Deletes an event tag called orientation.
   * `delete John Doe` : Deletes the contact named John Doe shown in the current list.
   * `exit` : Exits the app.
   * `import` : Imports contacts from `.\import\import.csv`.
   * `search Friend` : Displays all contacts with the tag `Friend`.
   * `switch Flag` : Switches to `Flag` event tab to show contacts.

7. Refer to the [Features](#features) below for details of each command or to [Command Summary](#command-summary)
for a quick summary.

<div style="page-break-after: always;"></div>

## Graphical User Interface (GUI)

![GUI](images/GUI_breakdown.png)

EventBook's GUI consists of 5 main components:

1. Menu Bar
2. Command Box - enter the command you would like to execute here.
3. Command Result Box - displays command success/error messages after commands are executed.
4. Event Tabs Panel - displays all existing Events.
5. Result Panel - displays contact details of members in the particular Event.

### Person

The following details are displayed for each contact in EventBook:
![GUI](images/Person.png)
This detailed overview enables users to quickly access essential contact information of their event members.

> [**Note**]
> <br>The default tab on entering the app is the `All` tab, with all contacts in EventBook displayed in the Result
> Panel.

<div style="page-break-after: always;"></div>

## Try Out Your First Commands!

Ready to dive in? Let's try out a few commands to get you started with EventBook:

1. Open EventBook if you haven't already done so by following the steps outlined in the [Quick Start](#quick-start)
   section.
2. First, let's start with **adding a new contact**. In the command box, type the following command: <br>

```
add n/Xavier Tan p/98765432 e/xavt@example.com a/Ang Mo Kio street 2, Block 123, #01-01
``` 

* This command will add a new contact named "Xavier Tan" with the provided phone number, email address, and address
  details.
* Scroll to the bottom of the list of contacts, you will find that Xavier Tan has been successfully added to your
  contacts!

3. Now, let's **tag** Xavier as the vice-project director `VPD` of the `Flag` event. Enter the following command in the
   command box:

```
assign Xavier Tan t/VPD t/E-Flag
```

* You will see Xavier has been assigned two new tags: a blue `VPD` tag and a purple `Flag` event tag.

4. Next, let's try **switching to the Flag event tab** to view its members. In the command box, type the following
   command: <br>

```
switch Flag
```

* Notice that the highlighted tab on the left switches from the `All` tab to the `Flag` tab, and all listed contacts
  have the `Flag` event tag. <br> 
**Note:**<br>
  The default tab on entering the app is the `All` tab, with all contacts in EventBook displayed in the Result Panel.
  </box>

5. To **switch back to the `All` tab** to view all your contacts, enter the following command:

```
list
```

6. Experiment with other basic commands that we have listed in the [Quick Start](#quick-start) section to get a feel
   for how EventBook works! <br>

Now that you are all warmed up, let's delve deeper into the details of each command and how you can use them to
supercharge your event management in the next section!

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Command Format

**Things to take note of before about the format of commands:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items in round brackets specify two possible parameter types
  e.g. `(NAME or INDEX)` means that either `NAME` or `INDEX` should fill that parameter

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend` (i.e. 1 time), `t/friend t/family` (i.e. multiple
  times) etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

> [**Note**]
> <br>If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple
> lines
> as space characters surrounding line-breaks may be omitted when copied over to the application.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

# Features

EventBook is divided into 3 categories - managing contacts, managing events with tags and general functions, which are all listed below.

## Managing Contacts
### Adding a person: `add`

This command allows you to add a person to the EventBook.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

> [**❗WARNING❗**]
> Names must consist **solely** of alphabetic characters; any inclusion of numbers or symbols is not permitted.
> This is to ensure that `delete 2` does not create an ambiguity on whether to delete the person with the name `2` or the person at index `2`.

> [**💡TIP💡**]
> <br>A person can have any number of tags (including 0)

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Editing a person : `edit`

This command allows you to edit an existing person in the EventBook.

Format: `edit (NAME or INDEX) [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX` or `NAME`.
* The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* The name refers to the name shown in the displayed person list. The name must be **exactly** what is shown in the displayed person list.
* For example, `edit John Doe t/friends` instead of `edit John t/friends`.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit Ben n/Betsy Crower t/` Edits the person named Ben to be `Betsy Crower` and clears all existing tags.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Finding a person with keywords : `find`

This command allows you to find all contacts whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

This command allows you to delete the specified person from the EventBook.

Format: `delete (NAME or INDEX)`

* Deletes the person at the specified `INDEX` or `NAME`.
* The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* The name refers to the name shown in the displayed person list. The name must be **exactly** what is shown in the displayed person list.
* For example, `delete John Doe` instead of `delete John`.

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the EventBook.
* `list` followed by `delete John Doe` deletes the person named 'John Doe' in the EventBook.

### Importing contacts from .csv : `import`

This command allows you to imports all contacts from given file path (default ./import/import.csv)

Format: `import f/PATH`

* Requires a valid file to be given in the `PATH` parameter
* If PATH is left empty (i.e. `import f/`) the default file referenced is `./import/import.csv`
* A sample is provided below. Note the fields in the first row and the data in subsequent rows.
  ![result for 'find alex david'](images/ImportSample.jpg)

* Fields:
  * The format of the first line of the csv should contain the fields:
    `NAME,NUMBER,EMAIL,ADDRESS,EVENTS,TAGS` as above (.csv is comma delimited, so that means 
    each field should be in their own cell)
  * The fields are non-caps sensitive (`Name` pr `NaME` would also be valid)
* Parameters:
  * The format of each parameter should follow the same format as in the add command format.
  * There should be no blank lines, and no conflicts with contacts in the existing EventBook (e.g. You cannot
    import a contact if EventBook already contains a contact with the exact same name)
  * There should be no trailing empty characters (' ') in any cell.
* The `EVENTS` parameter must satisfy one of the following format requirements:
    1. `N/A` if the contact is not in any events, else a field error will occur
    2. `t/E-eventName dc/Description sd/yyyy-MM-dd HH:mm:ss ed/yyyy-MM-dd HH:mm:ss` for the first instance of each
       unique event, unless said tag already exists in the EventBook, in which case iii. can be used
    3. `t/E-eventName` for existing events or previously declared events
        * e.g. If the first contact has `t/E-orientation dc/Orientation! sd/2024-04-04 02:02:02 ed/2024-04-05 02:02:02`,
          then further contacts may simply have `t/E-orientation` under the `EVENTS` fields
* The `TAGS` and `EVENTS` portion accept multiple arguments delimited by the `|` character (e.g. Friend|Colleague). A
  consequence of this is that tags containing `|` in their name cannot be imported
* Ensure that the file provided is saved before used as an import file
* The default download contains a sample .csv file that one can refer to

Examples:
* `import f/./import/import.csv` imports the contacts from the specified file
* `import f/` is identical to running the above command

### Exporting contacts from EventBook : `export`

This command allows you to export all contacts to ./export/export.csv

Format: `export`

* Note that moving files around may cause export to fail. If one relocates the source files, one must
  ensure that the ./export directory exists for the export.csv to generate at
* If a file export.csv is already present at ./export, it will be overwritten with the
  current EventBook data
* The export file format is the same as the output format given in `import`

> [**❗WARNING❗**]
> There should be an empty folder named `export` inside the folder with `EventBook.jar` to ensure that export function works.
>

<div style="page-break-after: always;"></div>

## Managing Events and Tags

### Creating a tag : `ctag`

This command allows you to create a tag or an event tag in the EventBook. You are able to manage or search for people with the specified tags after creation.

The `ctag` function allows you to create tags and events tags separately based on different input formats listed below.

Format 1: `ctag TAG_NAME`

This format allows you to create tags that you can tag your contacts with!

* The provided name has to be a **_unique_** `tag` name that does not already exist in the EventBook.
* The field "TAG_NAME" allows only alphanumerical inputs, eg. `0-9`,`a-z,A-Z`.
* The tag must be a single word. (e.g. `Friends` is allowed but `Good Friends` is forbidden)
* No trailing or extra whitespaces are allowed.

Example: `ctag Friend` creates a tag named `Friend`.

> [**❗WARNING❗**]
> 1. All input fields are necessary.
> 2. If you miss out any of the mandatory fields, or has input the wrong format, your input will get rejected or you will get an incorrect result.
> 3. Make sure your TAG_NAME is alphanumerical(`0-9`,`a-z,A-Z`) with no whitespaces in between them.

> [**💡TIP💡**]
> 1. If you want to create a tag with 2 words, consider grouping them together into a single word. For example: "
     SchoolFriends"

Format 2: `ctag t/E-EVENT_NAME dc/Description sd/yyyy-MM-dd HH:mm:ss ed/yyyy-MM-dd HH:mm:ss`

This format allows you to create event tags that you can tag your contacts with! How cool!

* The provided name has to be a **_unique_** `EVENT_NAME` name that does not already exist in the EventBook.
* The field "EVENT_NAME" allows only alphanumerical inputs, eg. `0-9`,`a-z,A-Z`. For instance, `ctag t/E-E-event1 sd/2024-04-04 02:02:02 ed/2024-04-05 02:02:02` is not allowed.
* The tag must be a single word.(e.g. `Meeting` is allowed but `Friends Meeting` is forbidden)
* No trailing or extra whitespaces are allowed.

Examples:

* `ctag t/E-orientation dc/Orientation! sd/2024-04-04 02:02:02 ed/2024-04-05 02:02:02`
* Creates an EventTag named orientation that starts from 2024-04-04 02:02:02 to 2024-04-05 02:02:02.

> [**💡TIP💡**]
> 1. If you want to create an event with 2 words, consider grouping them together into a single word. For example: "
     SchoolEvent"

> [**❗WARNING❗**]
> 1. All input fields are necessary.
> 2. If you miss out any of the mandatory fields, or has input the wrong format, your input will get rejected, or you
     will get an incorrect result.
> 3. Make sure your EVENT_NAME is alphanumerical(`0-9`,`a-z,A-Z`) with no whitespaces in between them.

### Deleting a tag : `dtag`

This command allows you to delete an existing tag in the EventBook. This is done to allow you to free up memory space by 
deleting tags that you will no longer use, how thoughtful of us :). 
Take note that this command also deletes the tag from all the contacts in your list.

Format: `dtag TAG_NAME`

* The provided name has to be a  `TAG_NAME` name that already exists in the EventBook.
* The field "TAG_NAME" allows only alphanumerical inputs, eg. `0-9`,`a-z,A-Z`.
* The tag must be a single word.(In fact, you can only create tags with single word inputs.)
* No trailing or extra whitespaces are allowed.
* The provided tag has to exist in the EventBook.

Examples:

* `dtag Friend` Deletes a tag named `Friend`.

> [**💡TIP💡**]
> 1. You can check who is associated with the tag you are deleting by using the [Search](#searching-by-tag--search) command.

> [**NOTE**]  
> If you delete a tag that does not exist, you will get notified by a warning that it does not exist. You will get a
> success message when you delete an existing tag too.

> [**❗WARNING❗**]
> All input fields are necessary.
> This tag will be removed from **every** contact tagged with it. This action is also **not** reversible.
> Make sure your `TAG_NAME` is alphanumerical(`0-9`,`a-z,A-Z`) with no whitespaces in between them.

### Deleting an EventTag : `devent`

This command allows you to delete an event tag in the EventBook. Convenient to do so when an event is over, right? 
Take note that this command also deletes the event tag from all the contacts that are associated with it.

Format: `devent EVENT_TAG`

* The provided name has to be an `EVENT_TAG` name that already exists in the EventBook.
* The field "EVENT_TAG" allows only alphanumerical inputs, eg. `0-9`,`a-z,A-Z`.
* The event tag must be a single word.(In fact, you can only create event tags with single word inputs.)
* No trailing or extra whitespaces are allowed.
* The provided event tag has to exist in the EventBook.

Examples:
* `devent orientation` Deletes an EventTag named `orientation`.

> [**💡TIP💡**]
> You can do a quick check of who is in the event by using the [Switch](#switching-between-events--switch), do take note that the event panel at the left of the GUI is not clickable.

> [**❗WARNING❗**]
> Make sure your `EVENT_NAME` is alphanumerical(`0-9`,`a-z,A-Z`) with no whitespaces in between them.

> [**NOTE**]  
> If you delete an event tag that does not exist, you will get notified by a warning that says it does not exist. You
> will get a success message after deleting an existing tag.
> The event GUI on the left of the screen will update when you delete an existing event.
> This event tag will be removed from **every** contact tagged with it. This action is also **not** reversible.

### Assigning a tag : `assign`

This command allows you to assign someone to a tag and group them conveniently! For example, you can assign a person with a tag named `Logistics` or assign a person with an event tag named `Rag`.

Format: `assign (NAME or INDEX) t/TAG…​` or `assign (NAME or INDEX) t/E-EVENT_TAG…​`

* Assign the person at the specified `INDEX` or `NAME` with the tag `TAG` or with the event tag `EVENT_TAG`.
* The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1,
  2, 3, …​
* The provided tag(s) and event tag(s) have to exist in the EventBook.
* No trailing or extra whitespaces are allowed.

Examples:
* `assign 1 t/Logistics` Assigns the first person in the list to a tag named `Logistics`.
* `assign John Doe t/E-event1` Assigns the person named John Doe in the list to an Event Tag named `event1`.

> [**💡TIP💡**]
> <br>If you do not have the tag or event tag you are assigning a person with, you can create them with
> the [ctag](#creating-a-tag--ctag) command.

> [**❗WARNING❗**]
> <br>The name refers to the name shown in the displayed person list. The name must be **exactly** what is shown in the
> displayed person list.
> For example, `assign John Doe t/friends` instead of `assign John t/friends`.

### Searching by tag : `search`

This command allows you to displays all the people that are tagged with `TAG_NAME`. We know that to search for a list people who are tagged as **Friend**,
you are tempted to do `search Friends`, plural. However, this is not supported :(.

Format: `search TAG_NAME`

* The search is case-sensitive.
* The provided tag has to exist in the EventBook.

Examples:

* `search Friend` Displays all people tagged as `Friend`.

> [**💡TIP💡**]
> <br>After searching, you can reset the app to display all the contacts with the [list](#listing-all-persons--list)
> command.

### Switching between events : `switch`

This command allows you to switch to the event with the `EVENT_TAG_NAME`.

Format: `switch EVENT_TAG_NAME`

* The search is case-sensitive.
* The default tab is the `All` tab with full list of contacts displayed.

Examples:
* `switch Flag` Switches to `Flag` event tab and shows all members tagged with `Flag` event tag.

> [**💡TIP💡**]
> After searching, you can reset the app to display all the contacts with the [list](#listing-all-persons--list)
command.

<div style="page-break-after: always;"></div>

## General Functions

### Getting help : `help`

To access the EventBook User Guide within the application, simply enter `help` into the command box and a link to the User Guide will be provided.

![help message](images/helpMessage.png)

Format: `help`

### Listing all contacts : `list`

This command shows a list of all contacts in the EventBook.

Format: `list`

### Clearing all entries : `clear`

This command clears all the contacts from the EventBook.

Format: `clear`

### Exiting the program : `exit`

This command exits the program.

Format: `exit`

### Saving the data

EventBook data are saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.

### Editing the data file

EventBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are
welcome to update data directly by editing that data file.

> [**❗WARNING❗**]
> <br>If your changes to the data file makes its format invalid, EventBook will discard all data and start with an empty
> data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
> Furthermore, certain edits can cause the EventBook to behave in unexpected ways (e.g., if a value entered is outside
> the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Setting Up Your First Event!

This section would guide you through some basic commands of EventBook, and how to use them to set up your first event
with contacts and tags.

### Clear sample data

To start, if you want to remove the sample contacts in the EventBook, you can use the `clear` command to clear
everything!
Simply type `clear` in the text box at the top of the app! (Do take note that throughout this tutorial, we will not
clear the
sample contacts for the convenience doing demo.)

1. Input `clear`.
2. Success! ![](images/UG/clear_after.png)

### Create a tag with `ctag`

To tag a person as a friend or any tag you want, you need to first create the tag.
To start, create a tag called `dummytag`. Do take not tags are alphanumerical, so you cannot do `pen-pal` unfortunately.

1. Input `ctag dummytag`.
2. Success! ![](images/UG/ctagtag_after.png)

### Using `assign` to tag people

To tag the person to the tag, we can use the assign command.

1. Assign the person of interest with your tag! For this example,
we assign Alex Yeoh as part of the `logistics` by typing `assign Alex Yeoh t/logistics`.
2. Success! ![](images/UG/assign_after.png)

### Using `switch` to change to the event that you want

Ok, I want to check who is in an event or the event details, how can I do it?
No worries pal, we got you covered. To figure out who is in any event, use the `switch` command.

1. Switch to the event named `Flag` with `switch Flag`.
2. Success! ![](images/UG/switch_after.png)

### Using `search` to find the people with the tag

You may want to say, what if I don't remember who are part of the logistics team?
No worries, you can use the search command to find out!

1. Search for the logistic team in the event `Flag` with `search logistics`.
2. Success! ![](images/UG/search_after.png)
3. To see all the existing contacts again, type `list` in the input box to reset everything!

### Delete a tag with `dtag`

Oh no, what if I want to delete a tag that I no longer use? No worries, you can use the `dtag` command!
This command removes the tag you delete for all your contacts so be careful!

1. Delete the existing `dummytag` tag using the dtag command `dtag dummytag`.
2. Success! ![](images/UG/dtag_after.png)

### Create an event with `ctag`

Ok, what if you have an event that you need to group people to, like a Orientation for 9 Feb 2024 8pm? No worries, you can assign people to events
and manage them as well. But first, you should create an event first with `ctag`.

1. Create an event called **Orientation** for 9 Feb 8pm with 
the command `ctag t/E-Orientation dc/SoC Orientation 2024 sd/2024-02-09 20:00:00 ed/2024-02-09 20:00:00`.
2. Success! ![](images/UG/cetag_after.png)

### Assign a person to the event with `assign`

Now you want to assign a person to the Rag. You can do it with the
`assign` command.

1. Assign Alex to the event `Rag` using `assign Alex Yeoh t/Rag`.
2. Success! ![](images/UG/assign_e_after.png)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Command summary

| Action              | Format, Examples                                                                                                                                                                             |
|---------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**             | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`                        |
| **Assign**          | `assign (NAME or INDEX) [t/TAG]…​` or `assign (NAME or INDEX) [t/E-TAG]…​` <br> e.g.,`assign 2 t/tag1` or `assign John Doe t/E-event1`                                                       |
| **Clear**           | `clear`                                                                                                                                                                                      |
| **Delete**          | `delete (NAME or INDEX)` <br> e.g., `delete 3` or `delete John Doe`                                                                                                                          |
| **Edit**            | `edit (NAME or INDEX) [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` <br> e.g., `edit 2 n/James Lee e/jameslee@example.com` or`edit John Doe n/James Lee e/jameslee@example.com` |
| **Find**            | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                   |
| **List**            | `list`                                                                                                                                                                                       |
| **Help**            | `help`                                                                                                                                                                                       |
| **Create Tag**      | `ctag TAG_NAME` <br> e.g., `ctag Friend`                                                                                                                                                     |
| **Create EventTag** | `ctag t/E-eventName dc/Description sd/yyyy-MM-dd HH:mm:ss ed/yyyy-MM-dd HH:mm:ss` <br> e.g., `ctag t/E-orientation dc/Orientation! sd/2024-04-04 02:02:02 ed/2024-04-05 02:02:02`            |
| **Delete Tag**      | `dtag TAG_NAME` <br> e.g., `dtag Friend`                                                                                                                                                     |
| **Delete Event**    | `devent EVENT_TAG_NAME` <br> e.g., `devent orientation`                                                                                                                                      |
| **Import**          | `import f/PATH` <br> e.g., `import f/./import/import.csv`                                                                                                                                    |
| **Export**          | `export`                                                                                                                                                                                     |
| **Search Tag**      | `search TAG_NAME` <br> e.g., `search Friend`                                                                                                                                                 |
| **Switch**          | `switch EVENT_TAG_NAME` <br> e.g. `switch Flag`                                                                                                                                              |

<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous EventBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. When giving enormously long inputs such as long tag/event tag names, the tag will not get fully displayed by the app. 
3. **Date Handling.** It has been identified in the Java Format library when user input an invalid date, eg: 31 Feb 2024, the system incorrectly accepts and converts it to a valid date such as 29 Feb 2024. This can result in incorrect date to be saved. Hence, it is recommended for users to manually verify that the date that they have written is accurate.

--------------------------------------------------------------------------------------------------------------------
