// 代码生成时间: 2025-10-27 17:18:48
package com.attendance;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    // Constructor with dependency injection
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    /**<ol>
     * Marks an employee as present for the current date.
     * @param employeeId The ID of the employee
     * @return The attendance record
     * @throws ResponseStatusException If the employee ID is invalid
     */
    public Attendance markAttendance(String employeeId) {
        if (employeeId == null || employeeId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee ID is required");
        }
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setCheckInTime(LocalDateTime.now());

        return attendanceRepository.save(attendance);
    }

    /**<ol>
     * Retrieves all attendance records for the current day.
     * @return A list of attendance records
     */
    public List<Attendance> getAllAttendanceToday() {
        return attendanceRepository.findAllByCheckInTimeBetween(LocalDateTime.now().toLocalDate().atStartOfDay(), LocalDateTime.now().plusDays(1).toLocalDate().atStartOfDay());
    }

    /**<ol>
     * Retrieves attendance records for a specific employee on the current day.
     * @param employeeId The ID of the employee
     * @return The attendance record or null if not found
     */
    public Attendance getAttendanceForEmployeeToday(String employeeId) {
        if (employeeId == null || employeeId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee ID is required");
        }
        List<Attendance> attendances = attendanceRepository.findAllByEmployeeIdAndCheckInTimeBetween(
            employeeId,
            LocalDateTime.now().toLocalDate().atStartOfDay(),
            LocalDateTime.now().plusDays(1).toLocalDate().atStartOfDay()
        );
        return attendances.stream().findFirst().orElse(null);
    }
}
