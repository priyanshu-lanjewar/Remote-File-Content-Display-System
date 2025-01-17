
<html>
<body>
<h1> Remote File Content Display System</h1><br><p>
This project is a simple client server-based communication application.
In this application, the client accepts the file name from the user;
Subsequently the client requests the server to send the file contents by
supplying the file name. The server has N number of files. From the
existing files, the server opens the requested file, reads the contents
line-by-line and sends each line separately one-by-one to the client.
Finally, the client displays the file name with file content.</p>
<h2>Features of Project </h2>
<ul>
<li> Can View Content of Files Stored on Server.
<li> Can Upload a File to Server.
<li> Server-Side Features
<ul>
<li> Graphical User Interface
<li> Option to Start, Stop and Configure Server
<li> Can Change Server Storage Location and Port number.
</ul>
<li> Client-Side Features
<ul>
<li> Option to enter server IP and Port.
<li> Option to Upload a File to Server.
<li> Option to search for a particular file on server and if present, view its content.
</ul>
</ul>
<h2> File Description </h2>
<ul>
<li>Server-Side Codes 
<ul>
<li><a href="https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/src/Server.java">Server.java</a>
<li><a href="https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/src/ConfigSetting.java">ConfigSetting.java</a>
<li><a href="https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/src/ServerConfiguration.java">ServerConfiguration.java</a>
<li><a href="https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/src/ServerThread.java">ServerThread.java</a>
</ul>
<li>Client-Side Codes 
<ul>
<li><a href="https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/src/SearchFileApp.java">SearchFileApp.java</a> 
<li><a href="https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/src/UploadFIle.java">UploadFile.java</a>
<li><a href="https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/src/FileViewer.java">FileViewer.java</a>
<li><a href="https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/src/ClientThread.java">ClientThread.java</a>
</ul>
</ul>
  <h2>Snapshots</h2>
  <li> Server Side App -> Initial Window : Configurations <br><br>
    <table border=2><td><img src = "https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/ScreenShots/Configuration%20Que%20first%20Screen.png"/></td></table><br>
      <li> Server Side App -> Dashboard <br><br>
   <table border=2><td><img src = "https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/ScreenShots/defaultConfServerBlank.png"/></td></table><br>
    <li> Server Side App -> Configuration Settings Updater <br><br>
   <table border=2><td><img src = "https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/ScreenShots/ChangeServerSetting.png"/></td></table><br>
      <li> Client Side App -> Server IP/port Request Window <br><br>
   <table border=2><td><img src = "https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/ScreenShots/ClientSideIpofServer.png"/></td></table><br>
        <li> Client Side App -> Search File Window <br><br>
   <table border=2><td><img src = "https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/ScreenShots/FileViewerDashboard.png"/></td></table><br>
          <li> Client Side App -> File Uploader <br><br>
   <table border=2><td><img src = "https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/ScreenShots/Uploader.png"/></td></table><br>
            <li> Client Side App -> File Viewer <br><br>
   <table border=2><td><img src = "https://github.com/priyanshu-lanjewar/Remote-File-Content-Display-System/blob/master/ScreenShots/fileF2Viewer.png"/></td></table><br>
</body>
</html>
