<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pending Access Requests</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            background-color: #f4f4f9;
        }
        .request-container {
            width: 80%;
            max-width: 800px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #dddddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        td form {
            display: inline;
        }
        button {
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .approve-btn {
            background-color: #4CAF50;
            color: white;
        }
        .approve-btn:hover {
            background-color: #45a049;
        }
        .reject-btn {
            background-color: #f44336;
            color: white;
        }
        .reject-btn:hover {
            background-color: #e31e1e;
        }
    </style>
</head>
<body>
<div class="request-container">
    <h2>Pending Access Requests</h2>
    <table>
        <thead>
            <tr>
                <th>Employee Name</th>
                <th>Software Name</th>
                <th>Access Type</th>
                <th>Reason for Request</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Dynamic rows for each pending request -->
            <tr>
                <td>John Doe</td>
                <td>Software A</td>
                <td>Read</td>
                <td>Need read access for project X.</td>
                <td>
                    <form action="ApprovalServlet" method="post">
                        <input type="hidden" name="requestId" value="1">
                        <input type="hidden" name="action" value="approve">
                        <button type="submit" class="approve-btn">Approve</button>
                    </form>
                    <form action="ApprovalServlet" method="post">
                        <input type="hidden" name="requestId" value="1">
                        <input type="hidden" name="action" value="reject">
                        <button type="submit" class="reject-btn">Reject</button>
                    </form>
                </td>
            </tr>
            <!-- Additional rows should be dynamically added here from the database -->
        </tbody>
    </table>
</div>
</body>
</html>
