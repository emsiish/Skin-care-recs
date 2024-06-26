# Skin-care-recs

## Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java:** Ensure you have Java installed on your machine. You can download it from [Java's official website](https://www.oracle.com/java/technologies/javase-downloads.html).

- **IntelliJ IDEA:** You will need IntelliJ IDEA as your integrated development environment (IDE). Download and install it from [IntelliJ IDEA's official website](https://www.jetbrains.com/idea/download/).

- **Node.js and npm:** Make sure you have Node.js and npm (Node Package Manager) installed. You can download them from [Node.js official website](https://nodejs.org/).

## Running the backend

1. Open the whole project in IntelliJ IDEA. It will suggest that the backend directory is a Maven project and will ask to load the dependencies.
2. Create env.properties file with the following:

   ```bash
   DB_USER=<your-db-user>
   DB_PASSWORD=<your-db-password>
   JWT_SECRET=<your-jwt-secret>
   AZURE_ENDPOINT=<your-blob-storage-endpoint>
   AZURE_KEY=<your-blob-storage-key>
   AZURE_ACCOUNT=<your-blob-storage-account>

3. After loading run the backend through the IDE.
4. The backend should be running on http://localhost:8080

## Running the frontend

To run the frontend, follow these steps:

1. Open a terminal and navigate to the frontend directory.

   ```bash
   cd frontend

2. After that install the required packages.

   ```bash
   npm install

3. Create .env file with the following:

   ```bash
   REACT_APP_API_BASE_URL=http://localhost:8080/api/v1

4. Finally, run the frontend application.

   ```bash
   npm start

4. The frontend should be running on http://localhost:3000.
