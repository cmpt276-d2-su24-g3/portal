# Group Proposal
Web Application Name: AWS YYC Portal
June 17th 2024, version 0 

## Problem
Latency is important to end-users for a variety of reasons. For end-user computers such as virtual desktops, the delay has a direct relation to productivity and employee experience. For enterprises, legacy applications require low-latency in order to migrate to AWS. However, AWS clients lack access to latency data from various geographic locations, making it difficult to choose the AWS region that offers optimal performance for their end-users. Additionally, AWS does not have the available services to view the ping time for their regions, thus, clients face delays in data transmission and application responsiveness, resulting in a decrease in user satisfaction.


## Project Abstract
The AWS YYC Portal is a website that allows users to view and interact with real-time and historical latency from various regions across North America to AWS regions. As such, users are capable of making decisions on which AWS region to use and monitor latency in real-time to respond to latency spikes and optimize their infrastructure. The latency will be displayed as iso-lines on a map-based interface, providing a visual representation of how ping time varies by geographic location. The application will also include a replay feature where users can select and view how the latency fluctuates throughout a specific time interval. Users can further sign-up or login to keep an activity log of latency history and gain personalized analytics. The end goal of the platform is to provide AWS clients with the knowledge to choose the optimal AWS region for their architecture, contributing to users migration to AWS. 


## Customer
The YYC Portal encompasses two main clients: AWS users and enterprise customers. AWS users are students, programmers and developers who are interested in AWS tools and services. AWS offers a range of services for various architectural needs, thus, their services appeal to developers and students looking to build and deploy scalable applications. The second demographic is compromised by enterprise customers. These are companies with applications in need of specific latency requirements as they migrate to AWS, contributing to the enterprise overall performance and customer satisfaction.


## Competitive Analysis
CloudPing (https://www.cloudping.cloud/aws) is a web application that enables users to send requests from their browser to various AWS regions and displays the corresponding ping times in a tabular format. While this tool serves certain use cases, it has limitations. Firstly, it does not account for variables that might affect the latency experienced by an average client situated in the same location. Secondly, the application does not cater to users who want to measure latency for a location different from their current one. Furthermore, it lacks the capability to display historical data, which could be valuable for identifying trends and anomalies over time.


## Solution
Our solution to the latency problem is to create a real-time ping website with three main components: A current and historical ping time provider, a data service for storing and retrieving ping times, and a ping-origination network. The ping time provider will include a map-based interface that displays iso-lines of ping times. Users will also be able to select the start and end time, which updates the ping-lines to show latency changes throughout the selected time interval. The data service will be an API endpoint that provides the ping times required for the website. It will also provide the endpoint for storing ping times, and will use caching to ensure consistent performance for end-users. Lastly, the ping-origination network will be obtained through CloudWatch Synthetics and also crowd-sourced through the users of the site. Upon user connection, the web-site pings the end-point for each region and sends that information back to the data-service.


## Features
The portal includes several key features: a Login system for user authentication, User Settings/Preferences, a Map Component for visualizing ping data, and a Chatbot Component to allow users to interact with the Chatbot AI. The Ping system features a Client - Endpoint Ping Service, AWS CloudWatch Synthetics (Canaries) System, Historic Ping Database, and Fetch Ping Data Service. The Chatbot AI employs retrieval-augmented generation and user context management using Langchain. It communicates with the frontend via FAST API endpoints. The chatbot will have knowledge about all AWS services and current status of AWS data centers, it also has all info currently shown to the user to provide a smoother and integrated response. Additionally, the Available Services Component lists services available to users and optionally fetches data from the AWS API Service.


## User Stories
### Login/Settings
### Social Media and Guest Login Option
Bob, a new user, wants to sign in to the web application to access its features. He is presented with an option to sign in using his username/password, social media, or as a guest.
a) Upon entering his credential, Bob will be redirected to the main application page with access to all features that require a login.
b) Upon choosing social media login, the user is redirected to the social media authentication page. Successful authentication redirects the user back to the application, logged in with social media credentials, with access to all features that require a login.
c) Upon continuing as a guest, Bob is redirected to the main application page without access to certain features that require login (chatbot chat history, saved preferences, etc.)
After the login, Bob should be on the main application page with access to features based on his privilege level (registered user vs guest)
Iteration: 1
### Password Reset
Alex, a registered user, cannot remember his password and wants to reset his password to regain access to his account. He is presented with an option to reset his password on the login page. Upon selecting the "Forgot Password" option, Alex will be prompted to enter his registered email address. The system will send a password reset link to the provided email address. Alex will then click on the reset link, which will redirect him to a page where he can enter a new password. After submitting the new password, the system updates Alex's password and notifies him of the successful reset. After the password reset, Alex should be back on the login page and the password associated with his account in the database should be changed.
Iteration: 1
### Ping
### Location-to-Region Ping
Tommy, an end-user of software that uses AWS, wants to select an AWS region and see the real-time latency between his house and the selected AWS data center. Tommy is presented with a map with points representing all data centers. After selecting a specific data center and confirming, the application pings the endpoint at the data center and presents Tommy with the latency. After the ping, Tommy should see the latency between the client and the selected region, and this information is saved onto a database for future reference.
Iteration: 1
### Region-to-Region Ping
Christine, a software engineer, wants to select a region and an AWS region to see the latest recorded latency between them so that she can see the latency between the AWS region and the geographical regions of her target users. Christine selects a geographical region and an AWS region from the available options. The system retrieves and displays the latest recorded latency data between the selected regions. The latency data helps her make informed decisions about AWS region deployments to ensure optimal user experience for her target audience. This information is also saved onto a database for future reference.
Iteration: 2
### Historical Ping
Josh, a user, wants to see the historical ping data between a region and an AWS region to see how the latency fluctuates throughout the day/week. After selecting a region and an AWS region, Josh selects Historical Data option. He is then presented with all the data in a specific time-range from the database. After using this feature, Josh should have historical data on the ping between the two regions.
Iteration: 2


## AI Chatbot
### AI Chatbot Access
Bobby, a regular user and programmer, logs into the AWS YYC Portal and wants to access the chatbot without it obstructing his view. He can open the chatbot via a visible icon, which minimizes when not in use. To ensure Bobby’s needs are met, developers will confirm the chatbot icon is always visible, opens without reloading the page, and does not cover essential parts when minimized. This will be tested to guarantee a smooth user experience.
Iteration: 2
### Chatbot Inquiries about AWS Calgary Data Center
Sarah, an enterprise IT specialist, seeks information about the AWS Calgary Data Center. She queries the chatbot, which provides accurate details on its features, capabilities, and current status. Developers will ensure the chatbot’s responses about the opening date, available services, and operational status are accurate and reliable through testing.
Iteration: 2
### Chatbot General AWS Services and Data Centers Inquiry
Carlos, a cloud architect, uses the chatbot to ask for the latest information on AWS services and data centers. Developers will verify that the chatbot provides current and clear information on AWS service statuses and data center capabilities. These tests will ensure Carlos receives the necessary information for informed decision-making.
Iteration: 2
### Chatbot Summarization and Explanation
Emma, a software engineer, needs clarification on the content displayed in the AWS YYC Portal. She uses the chatbot for explanations and summaries. Developers will test the chatbot to ensure it accurately summarizes latency data, explains specific terms, and provides summaries of historical trends, thereby aiding Emma's understanding.
Iteration: 2


Available Services (Optional)
Margot, a software engineer, wants to see the list of services currently available in a specific AWS region so that she can make an informed decision on which AWS region she wants to use. By selecting the Available Services button, she is presented with all the AWS regions to choose from. After choosing a specific region, she is shown the list of all available services in that region.
Iteration: 3
