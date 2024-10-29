<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Software</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f4f4f9;
        }
        .software-container {
            width: 350px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333333;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #cccccc;
            border-radius: 4px;
        }
        .checkbox-group {
            display: flex;
            justify-content: space-between;
            margin-bottom: 15px;
        }
        .checkbox-group label {
            font-weight: normal;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            border: none;
            color: white;
            font-weight: bold;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="software-container">
    <h2>Create Software</h2>
    <form action="SoftwareServlet" method="post">
        <label for="name">Software Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="4" required></textarea>

        <label>Access Levels:</label>
        <div class="checkbox-group">
            <label><input type="checkbox" name="accessLevels" value="Read"> Read</label>
            <label><input type="checkbox" name="accessLevels" value="Write"> Write</label>
            <label><input type="checkbox" name="accessLevels" value="Admin"> Admin</label>
        </div>

        <input type="submit" value="Create Software">
    </form>
</div>
</body>
</html>
