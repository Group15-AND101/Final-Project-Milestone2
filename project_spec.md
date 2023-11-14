# **WorldView**

## Table of Contents

1. [App Overview](#App-Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Build Notes](#Build-Notes)

## App Overview

### Description & Evaluation
<!-- Evaluation of your app across the following attributes -->

- **Description**: Display data about global metrics (e.g., population, number of cars on the road, etc.) as well as other intriguing pieces of data (images, etc.).
- **Category**: All-purpose/Information
- **Story**: This application provides data to inform users about the state of human civilization through the presentation of metrics and other data. Think of WorldView as the front page of your phone for global awareness.
- **Market**: General population, data enthusiasts, educators, students.
- **Habit**: Used for gaining objective understanding about world state, educational purposes, or satisfying curiosity about global statistics.
- **Scope**: Utilizing multiple views to display the output of various APIs. Initial focus on fundamental data presentation, with potential to expand based on time and resources.
## Product Spec

### 1. User Features (Required and Optional)

Required Features:

- **Display one or more views (TextView, ImageView, etc.) using a RecyclerView**
- **Implement a way to refresh data**
- **Display data by country/region**

Stretch Features:

- **Display game data of choice**
- **Display fashion data of choice**

### 2. Chosen API(s)
- **World Bank API: (https://documents.worldbank.org/en/publication/documents-reports/api)**
- - Get Country and Lending Group Data: Present information about different countries, including population sizes, income levels, and classifications.

- - Get Income Level Data: Display the distribution of the world's income levels and poverty rates to inform users about economic disparities.

- **Country API: (https://api-ninjas.com/api/country)**
- **Country Query API: (https://datahelpdesk.worldbank.org/knowledgebase/articles/898590-country-api-queries)**

### 3. User Interaction

Required Feature

- **user action (Refresh button - onClick)**
  - => **data will be refreshed**
  - **display random ordered list of countries**
- **user action (Get WorldView - text entry, onclick)**
    - **user enter country name**
    - =>**Displayed sorted data by country name**
    
## Wireframes

<!-- Add picture of your hand sketched wireframes in this section -->
We decided it would be easier to collaborate using Figma instead of doing hand sketched wireframes:
<img src="https://i.imgur.com/ZolSfxM.png" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Build Notes

Here's a place for any other notes on the app, it's creation 
process, or what you learned this unit!  

For Milestone 2, include **2+ Videos/GIFs** of the build process here!

## License

Copyright **2023** **Asit Das, Yen Nyugen, Romaine Dzeinse, Arthur Cheung**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
