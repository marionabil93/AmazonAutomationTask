# Amazon Egypt Automation Framework

## Overview
This project automates the workflow on Link (https://www.amazon.eg/) using Selenium with Java, TestNG, and Maven. The script logs in, navigates to the "All Video Games" section, applies filters, sorts products, adds items below 15,000 EGP to the cart, and verifies the total.

## Features
- Automated login to Amazon Egypt.
- Navigation through the "All" menu.
- Selection of "Video Games" -> "All Video Games".
- Filtering by "Free Shipping" and "New" condition.
- Sorting products by "Price: High to Low".
- Adding all products below 15,000 EGP to the cart.
- Pagination if no qualifying products are found on the current page.
- Verifying items in the cart.
- Ensuring accurate total price calculation.

## Prerequisites
Ensure the following are installed before running the automation:
- **Java** 
- **Maven** (for dependency management)
- **Google Chrome** (latest version)
-  **IDE** (IntelliJ IDEA)
## Installation
1. **Clone the Repository**:
   ```sh
   git clone <repository-url>
   cd cd <repository-folder>
   ```
2. **Set Up Dependencies**:
    - Ensure `pom.xml` includes dependencies for:
        - Selenium WebDriver
        - TestNG
        - WebDriverManager
        - Log4j2 (for logging)
    - Run:
      ```sh
      mvn clean install
      ```

## Configuration
- Update `config.properties` with:
  ```properties
  baseUrl=https://www.amazon.eg/
  username=your-email@example.com
  password=your-secure-password
  ```

## Running the Tests
Run tests using TestNG:
```sh
mvn test
```

## Logging & Debugging
- Logs are stored in `logs/automation.log`.
- For debugging, screenshots are captured in `screenshots/` on failure.

## Reporting
- TestNG reports are generated in `target/surefire-reports/index.html`.


