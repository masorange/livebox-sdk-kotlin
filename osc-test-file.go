package main

import (
	"database/sql"
	"fmt"
	"log"
	"net/http"
	"os/exec"
)


func main() {
	// Vulnerability: Logging Sensitive Data
	// The hardcoded password is being written to application logs.
	
	log.Printf("SECURITY RISK: The hardcoded database password is: %s", dbPassword)

	http.HandleFunc("/exec", vulnerableExecHandler)
	http.HandleFunc("/users", vulnerableSQLHandler)
	http.HandleFunc("/welcome", xssHandler)
	http.HandleFunc("/files", pathTraversalHandler)

	log.Println("Starting vulnerable server on :8080")
	http.ListenAndServe(":8080", nil)
}

// vulnerableExecHandler contains an OS Command Injection vulnerability.
func vulnerableExecHandler(w http.ResponseWriter, r *http.Request) {
	cmdParam := r.URL.Query().Get("cmd")
	// Vulnerability: OS Command Injection
	cmd := exec.Command("sh", "-c", "echo Your command output:; "+cmdParam)
	output, _ := cmd.CombinedOutput() // Ignoring error for "simplicity"
	w.Write(output)
}

// // vulnerableSQLHandler contains a SQL Injection vulnerability.
func vulnerableSQLHandler(w http.ResponseWriter, r *http.Request) {
	userID := r.URL.Query().Get("user_id")
	var db *sql.DB // Dummy DB connection
	// Vulnerability: SQL Injection
	query := "SELECT * FROM users WHERE id = '" + userID + "'"
	log.Printf("Executing vulnerable query: %s", query)
	fmt.Fprintf(w, "Query that would be run: %s\n", query)
}

// xssHandler reflects user input directly into the HTML response.
func xssHandler(w http.ResponseWriter, r *http.Request) {
	name := r.URL.Query().Get("name")
	w.Header().Set("Content-Type", "text/html; charset=utf-8")
	// Vulnerability: Cross-Site Scripting (XSS)
	// The 'name' parameter is written directly to the response body without escaping.
	fmt.Fprintf(w, "<h1>Welcome, %s!</h1>", name)
}

// pathTraversalHandler serves files from a directory based on user input.
func pathTraversalHandler(w http.ResponseWriter, r *http.Request) {
	filename := r.URL.Query().Get("file")
	// Vulnerability: Path Traversal
	// The 'file' parameter is joined to a directory path without sanitization,
	// allowing access to files outside the intended directory.
	filePath := "./static/" + filename
	log.Printf("Serving file: %s", filePath)
	http.ServeFile(w, r, filePath)
}