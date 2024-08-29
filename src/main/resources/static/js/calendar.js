// Function to fetch events from the server
async function fetchEvents(year, month) {
    // Replace with your actual API endpoint
    const response = await fetch(`http://localhost:8080/api/calendar/${month}/${year}`);
    const events = await response.json();
    return events; // Assuming the returned data is an array of objects
}

// Function to generate the calendar
async function generateCalendar(year, month ) {
    const events = await fetchEvents(year, month + 1); // Fetch events for the month
    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month - 1, 0);
    const daysInMonth = lastDay.getDate();
    const startingDay = firstDay.getDay();

    const calendarBody = document.getElementById("calendar-body");
    calendarBody.innerHTML = "";

    let dayCounter = 1;

    for (let i = 0; i < 6; i++) {
        const row = document.createElement("tr");

        for (let j = 0; j < 7; j++) {
            const cell = document.createElement("td");
            const a = document.createElement("a");
            a.href = "#";
            a.style.color = "black";
            a.style.textDecoration = "none";
            if (i === 0 && j < startingDay) {
                // Empty cells before the first day of the month
                cell.textContent = "";
            } else if (dayCounter <= daysInMonth) {
                a.textContent = dayCounter.toString();

                // Check for events on this day
                const dayEvents = events.filter(event => event.day === dayCounter);
                dayEvents.forEach(event => {
                    const span = document.createElement("span");
                    span.textContent = event.description; // Add event description
                    span.style.color = "black";
                    span.style.fontFamily = "Lobster"
                    span.style.fontSize = "18px"// Customize the style as needed
                    span.style.display = "block"; // Display each event on a new line
                    a.appendChild(span);
                });

                cell.appendChild(a);
                a.href = `/calendar/${month + 1}/${dayCounter}/${year}`;
                dayCounter++;
            }
            row.appendChild(cell);
        }

        calendarBody.appendChild(row);
    }

    document.getElementById("currentMonth").textContent =
        new Intl.DateTimeFormat("bg-BG", { month: "long", year: "numeric" }).format(firstDay);
}

function previousMonth() {
    currentYear = (currentMonth === 1) ? currentYear - 1 : currentYear;
    currentMonth = (currentMonth === 1) ? 12 : currentMonth - 1;
    generateCalendar(currentYear, currentMonth);
}

function nextMonth() {
    currentYear = (currentMonth === 12) ? currentYear + 1 : currentYear;
    currentMonth = (currentMonth % 12) + 1;
    generateCalendar(currentYear, currentMonth);
}

// Initial setup
let currentYear = new Date().getFullYear();
let currentMonth = new Date().getMonth();
generateCalendar(currentYear, currentMonth);
