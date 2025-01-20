<p align="center">
    <img src="https://dl.photoprism.app/img/logo/logo-app.svg" align="center" width="30%">
</p>
<p align="center"><h1 align="center">PHOTOPRISMPROJECT</h1></p>
<p align="center">
	<em>PhotoPrism Ui & API Testing</em>
</p>
<p align="center">
	<img src="https://img.shields.io/github/last-commit/ahmadnas00/PhotoprismProject?style=default&logo=git&logoColor=white&color=0080ff" alt="last-commit">
	<img src="https://img.shields.io/github/languages/top/ahmadnas00/PhotoprismProject?style=default&color=0080ff" alt="repo-top-language">
	<img src="https://img.shields.io/github/languages/count/ahmadnas00/PhotoprismProject?style=default&color=0080ff" alt="repo-language-count">
</p>
<p align="center"><!-- default option, no dependency badges. -->
</p>
<p align="center">
	<!-- default option, no dependency badges. -->
</p>
<br>

##  Table of Contents

- [ Overview](#-overview)
- [ Features](#-features)
- [ Project Structure](#-project-structure)
  - [ Project Index](#-project-index)
- [ Getting Started](#-getting-started)
  - [ Prerequisites](#-prerequisites)
  - [ Installation](#-installation)
  - [ Usage](#-usage)
  - [ Testing](#-testing)
- [ Contributing](#-contributing)

---

##  Overview

PhotoPrism is an AI-powered open-source platform designed for managing, organizing, and sharing photos. It allows users to upload, create albums, and sort images using advanced filters like places, dates, colors, and faces. With options to mark favorites, toggle views, and securely share content,.

---

##  Features

|      | Feature         | Summary       |
| :--- | :---:           | :---          |
| ⚙️  | **Architecture**  | <ul><li>Uses a combination of Java and YAML for its architecture.</li><li>Organized into separate workflows for different testing scenarios.</li><li>Codebase is divided into main and test directories, with further organization based on functionality.</li></ul> |
| 🔩 | **Code Quality**  | <ul><li>Code is well-structured and organized into separate classes and methods based on functionality.</li><li>Follows good coding practices with clear and concise comments.</li><li>Uses GitHub Actions for continuous integration and testing.</li></ul> |
| 📄 | **Documentation** | <ul><li>Primary language used is `Java`.</li><li>Language counts indicate a higher usage of `Java` (9) compared to `YAML` (4).</li><li>Each file and its functionality is well-documented.</li></ul> |
| 🔌 | **Integrations**  | <ul><li>Integrates with GitHub Actions for automated testing.</li><li>Uses Selenium Grid for cross-browser testing.</li><li>Supports both local and remote execution of tests.</li></ul> |
| 🧩 | **Modularity**    | <ul><li>Code is modular with separate classes for different functionalities.</li><li>Test cases are organized into separate files based on the functionality they test.</li><li>Uses a DriverFactory class to manage WebDriver instances.</li></ul> |
| 🧪 | **Testing**       | <ul><li>Uses automated testing for both API and UI.</li><li>Supports testing on different browsers (Chrome and Firefox).</li><li>Includes tests for different functionalities such as login, features, filter and edit, and API.</li></ul> |
| ⚡️  | **Performance**   | <ul><li>Uses automated testing to ensure optimal performance.</li><li>Supports testing on different grid URLs for performance evaluation.</li><li>Includes tests for performance-critical features such as image uploading and search functionality.</li></ul> |
| 🛡️ | **Security**      | <ul><li>Includes tests for login functionality to ensure security.</li><li>Tests both successful and unsuccessful login scenarios.</li><li>Uses GitHub Actions for secure CI/CD pipeline.</li></ul> |
| 📦 | **Dependencies**  | <ul><li>Dependencies include `github_actions`, `ui_login.yaml`, `java`, `ui_feature.yaml`, `api.yaml`, `ui_filter.yaml`.</li><li>Uses Selenium WebDriver for browser automation.</li><li>Does not appear to have any package managers or containers.</li></ul> |
| 🚀 | **Scalability**   | <ul><li>Supports testing on different grid URLs, allowing for scalability.</li><li>Modular codebase allows for easy addition of new features and tests.</li><li>Uses GitHub Actions for scalable CI/CD pipeline.</li></ul> |

---




###  Project Index
<details open>
	<summary><b><code>PHOTOPRISMPROJECT/</code></b></summary>
	<details> <!-- .github Submodule -->
		<summary><b>.github</b></summary>
		<blockquote>
			<details>
				<summary><b>workflows</b></summary>
				<blockquote>
					<table>
					<tr>
						<td><b><a href='https://github.com/ahmadnas00/PhotoprismProject/blob/master/.github/workflows/API.yaml'>API.yaml</a></b></td>
						<td>- API.yaml orchestrates automated testing for the project, specifically for Chrome and Firefox browsers<br>- It sets up the testing environment, launches standalone grids, and executes tests on specified browser versions<br>- The file also allows for manual triggering of tests and supports testing on different grid URLs.</td>
					</tr>
					<tr>
						<td><b><a href='https://github.com/ahmadnas00/PhotoprismProject/blob/master/.github/workflows/UI_Feature.yaml'>UI_Feature.yaml</a></b></td>
						<td>- UI_Feature.yaml orchestrates automated testing for user interface features<br>- It initiates tests on both Chrome and Firefox browsers, leveraging GitHub Actions and Selenium Grid<br>- The tests run on a specified grid URL, with the option to run locally as a default<br>- The file ensures the codebase's UI functionality remains consistent across different browser versions.</td>
					</tr>
					<tr>
						<td><b><a href='https://github.com/ahmadnas00/PhotoprismProject/blob/master/.github/workflows/UI_Filter.yaml'>UI_Filter.yaml</a></b></td>
						<td>- UI Filter and Edit testing is a GitHub workflow that facilitates automated testing on different browsers, specifically Chrome and Firefox<br>- It sets up the testing environment, launches standalone grids, runs tests, and finally tears down the grid<br>- This workflow is crucial for ensuring the robustness and reliability of the user interface across different browser versions.</td>
					</tr>
					<tr>
						<td><b><a href='https://github.com/ahmadnas00/PhotoprismProject/blob/master/.github/workflows/UI_Login.yaml'>UI_Login.yaml</a></b></td>
						<td>- UI_Login.yaml orchestrates automated testing for the user interface login functionality<br>- It sets up testing environments for both Chrome and Firefox browsers on an Ubuntu system, utilizing Selenium standalone grids<br>- The tests are executed upon workflow dispatch, with the option to specify the grid URL and browser versions.</td>
					</tr>
					</table>
				</blockquote>
			</details>
		</blockquote>
	</details>
	<details> <!-- src Submodule -->
		<summary><b>src</b></summary>
		<blockquote>
			<details>
				<summary><b>main</b></summary>
				<blockquote>
					<details>
						<summary><b>java</b></summary>
						<blockquote>
							<details>
								<summary><b>org</b></summary>
								<blockquote>
									<details>
										<summary><b>TestAutomationProject</b></summary>
										<blockquote>
											<table>
											<tr>
												<td><b><a href='https://github.com/ahmadnas00/PhotoprismProject/blob/master/src/main/java/org/TestAutomationProject/myLoginpage.java'>myLoginpage.java</a></b></td>
												<td>- myLoginpage.java, located in the src/main/java/org/TestAutomationProject directory, serves as the automation script for the login functionality of the application<br>- It validates both correct and incorrect user credentials, ensuring proper navigation to the landing page or remaining on the login page respectively<br>- This contributes to the overall testing efficiency of the project.</td>
											</tr>
											<tr>
												<td><b><a href='https://github.com/ahmadnas00/PhotoprismProject/blob/master/src/main/java/org/TestAutomationProject/Favorites.java'>Favorites.java</a></b></td>
												<td>- Favorites, located in the TestAutomationProject package, manages user interactions with the favorites feature of a web application<br>- It provides functionality for searching favorites by title, retrieving the title of the first image, and verifying if a title is present in the favorites<br>- This class plays a crucial role in automating user interface tests for the favorites feature.</td>
											</tr>
											<tr>
												<td><b><a href='https://github.com/ahmadnas00/PhotoprismProject/blob/master/src/main/java/org/TestAutomationProject/Archive.java'>Archive.java</a></b></td>
												<td>- Archive serves as a key component in the TestAutomationProject, managing the archive section of a web application<br>- It provides functionalities such as searching by title, selecting, restoring, and deleting images, as well as checking if the archive is empty<br>- This class interacts directly with the web elements, ensuring seamless user experience.</td>
											</tr>
											<tr>
												<td><b><a href='https://github.com/ahmadnas00/PhotoprismProject/blob/master/src/main/java/org/TestAutomationProject/Landingpage.java'>Landingpage.java</a></b></td>
												<td>- Landingpage.java serves as a key component of the TestAutomationProject, providing a suite of functions for interacting with the landing page of an AI-Powered Photos App<br>- It enables navigation to different sections, image uploading, search functionality, and various user interactions such as favoriting images, toggling views, and applying filters.</td>
											</tr>
											<tr>
												<td><b><a href='https://github.com/ahmadnas00/PhotoprismProject/blob/master/src/main/java/org/TestAutomationProject/Review.java'>Review.java</a></b></td>
												<td>- Review.java serves as a critical component in the TestAutomationProject, enabling automated testing of the review functionality in a web application<br>- It provides capabilities to approve images and navigate to the landing page<br>- Additionally, it includes a DriverFactory class to manage WebDriver instances, supporting both local and remote execution in different browsers.</td>
											</tr>
											</table>
										</blockquote>
									</details>
								</blockquote>
							</details>
						</blockquote>
					</details>
				</blockquote>
			</details>
			<details>
				<summary><b>test</b></summary>
				<blockquote>
					<details>
						<summary><b>java</b></summary>
						<blockquote>
							<details>
								<summary><b>TestAutomationProjectTesting</b></summary>
								<blockquote>
									<table>
									<tr>
										<td><b><a href='https://github.com/ahmadnas00/PhotoprismProject/blob/master/src/test/java/TestAutomationProjectTesting/ApiTest.java'>ApiTest.java</a></b></td>
										<td>- ApiTest.java is a test suite for validating the functionality of a photo management API<br>- It verifies various operations such as previewing photos, favoriting and unfavoriting images, searching for photos, applying filters, and managing photo privacy and archival<br>- The tests ensure that the API responds correctly and delivers the expected results.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/ahmadnas00/PhotoprismProject/blob/master/src/test/java/TestAutomationProjectTesting/FeaturesTest.java'>FeaturesTest.java</a></b></td>
										<td>- FeaturesTest.java validates the functionality of a web application's user interface<br>- It conducts a series of tests on the landing page, including checking if the gallery is empty, testing the reload button, searching by title, marking favorites, uploading and approving images, and deleting images<br>- The tests ensure the application's features work as expected.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/ahmadnas00/PhotoprismProject/blob/master/src/test/java/TestAutomationProjectTesting/FilterAndEditTest.java'>FilterAndEditTest.java</a></b></td>
										<td>- FilterAndEditTest, located in the test directory, serves as a comprehensive suite of automated tests for the application's filtering and editing functionalities<br>- It validates various features such as toggling views, filtering by different parameters, image selection, archiving, and sharing<br>- The tests ensure the robustness and reliability of the application's user interface.</td>
									</tr>
									<tr>
										<td><b><a href='https://github.com/ahmadnas00/PhotoprismProject/blob/master/src/test/java/TestAutomationProjectTesting/LoginTest.java'>LoginTest.java</a></b></td>
										<td>- LoginTest, located in the test directory, validates the functionality of the login process in the application<br>- It conducts two tests: one for a successful login using valid credentials, and another for an unsuccessful login with invalid credentials<br>- The tests ensure the application's security and user authentication processes are functioning as expected.</td>
									</tr>
									</table>
								</blockquote>
							</details>
						</blockquote>
					</details>
				</blockquote>
			</details>
		</blockquote>
	</details>
</details>

---
##  Getting Started

###  Prerequisites

Before getting started with PhotoprismProject, ensure your runtime environment meets the following requirements:

- **Programming Language:** Java


###  Installation

Install PhotoprismProject using one of the following methods:

**Build from source:**

1. Clone the PhotoprismProject repository:
```sh
❯ git clone https://github.com/ahmadnas00/PhotoprismProject
```

2. Navigate to the project directory:
```sh
❯ cd PhotoprismProject
```

3. Install the project dependencies:
```sh
❯ npm install
```

4. Build the project (if required):
```sh
❯ npm run build
```



###  Usage

Run PhotoprismProject using the following command:
```sh
❯ npm start
```

Open the application in your browser by visiting:
```sh
❯ http://localhost:2342
```
(or the port specified in your configuration, e.g., 8080).

###  Testing
Run the test suite using the following command:

```sh
❯ npm test
```

---


<details closed>
<summary>Contributing Guidelines</summary>

1. **Fork the Repository**: Start by forking the project repository to your github account.
2. **Clone Locally**: Clone the forked repository to your local machine using a git client.
   ```sh
   git clone https://github.com/ahmadnas00/PhotoprismProject
   ```
3. **Create a New Branch**: Always work on a new branch, giving it a descriptive name.
   ```sh
   git checkout -b new-feature-x
   ```
4. **Make Your Changes**: Develop and test your changes locally.
5. **Commit Your Changes**: Commit with a clear message describing your updates.
   ```sh
   git commit -m 'Implemented new feature x.'
   ```
6. **Push to github**: Push the changes to your forked repository.
   ```sh
   git push origin new-feature-x
   ```
7. **Submit a Pull Request**: Create a PR against the original project repository. Clearly describe the changes and their motivations.
8. **Review**: Once your PR is reviewed and approved, it will be merged into the main branch. Congratulations on your contribution!
</details>


---

---
