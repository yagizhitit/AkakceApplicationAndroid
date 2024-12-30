# Akakce App with Android

This project is an e-commerce application developed using **Kotlin** and **XML layouts**. It enables users to browse products in horizontal and vertical lists, view detailed information about selected products, and enjoy a modern and dynamic user experience.

## Features 🔧

- **Horizontal Product List (CardView)**
  - Displays a horizontally scrollable list of products, limited to 5 items.

- **Vertical Product List (GridView)**
  - Displays products in a grid layout with two items per row.

- **Product Details**

  - Detailed information about the selected product, including:
       - Category
       - Price
       - Rating (with stars filled proportionally)
       - Description

- **Dynamic Data Fetching**
  - Products are fetched from the Fake Store API.


## Technologies Used 🚀
  - **Kotlin:** The programming language.
  - **RecyclerView:** For displaying horizontal and vertical product lists.
  - **Retrofit:** To handle API calls and fetch data.
  - **Glide:** For loading product images.
  - **XML Layouts:** For designing the user interface.
  - **ConstraintLayout:** For creating responsive layouts.

## How It Works 📱

- **Home Screen**
  - Browse products in horizontal and vertical lists.
    - Horizontal list: Limited to 5 products, displayed in a scrollable RecyclerView.
    - Vertical list: Displays products in a grid layout with two items per row.

- **Clicking on a Product**
  - Opens the Product Detail Page, displaying all available details about the selected product.

- **Product Detail Page**
  - View the product’s:
  - Category
  - Price
  - Description
  - Rating (with dynamically filled stars based on the rating value).


## Screenshots 🖼️

**Home Screen**

![HomePageAndroid](https://github.com/user-attachments/assets/e3d5f5c7-df4a-48cb-a6fa-58088acf6dd4)

**Product Detail Page**

![DetailPageAndroid](https://github.com/user-attachments/assets/b79e4122-8cdf-43d9-865d-79776059c38f)