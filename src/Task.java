class Task {
    String title, description, status;

    public Task(String title, String description, String status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(" [%s] %s", status.toUpperCase(), title);
    }
}