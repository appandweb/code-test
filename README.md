# Android Code Test

👋 Hello there!

To see how you work in an existing codebase, and save you the trouble of writing too much boilerplate, we're giving you this App as a base for the Code Test.

This is a sample Master-Detail App featuring a list of users from [Random User Api](https://randomuser.me/). Your goal is to add a User Detail screen.
You need to implement a mechanism to pass the clicked user from the UserList to the UserDetail screen. Ideally only pass an identifier, and not the whole entity.
Design a UserDetail screen of your choice to display additional information of the user that has been clicked on the list

## Please add the following functionality:

- Add a UserDetailActivity showing User's name, email, and picture
- Implement backwards navigation, ideally using a Navigation Button
- Provide Unit Tests for every part of the code you implement

We will both read your code and run the app, so please check that your code is clean and you provide a good user experience.
We will also run checkstyle to see your code matches the code style rules. You can check if your code passes the code style check by writing:

    ./gradlew checkstyle

You are welcome to change any part of the provided code, but you should be ready to explain any changes that you make.

You can use Java or Kotlin as the programming language. Choose the one you prefer for this exercise

## Valuable points:

- MVP-based architecture with good naming conventions for your classes
- Delightful UI experience
- Use a Toolbar for backwards navigation
- Adding some more fields
- Clean history. Small, and readable commits
- Passing only an identifier to UserDetail screen instead of the entire entity. No parcelables or Serializables involved

⏰ We don't want you to spend to much time on the Code Test so we tried to keep it short. Anyway, this is an open-ended Code Test, so feel free to take your time.

Please submit your solution as a GitHub repository within **one week** from receiving these instructions. Make [@appandweb](https://github.com/appandweb) a collaborator when it is ready to be reviewed

If you have any question or a suggestion for improvement, please let us know.

We're looking forward to receiving your solution. Good luck!