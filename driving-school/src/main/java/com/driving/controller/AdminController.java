// src/main/java/com/driving/controller/AdminController.java
package com.driving.controller;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 获取所有学员列表
     */
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(adminService.getAllStudents(page, size));
    }

    /**
     * 创建新教练账号
     */
    @PostMapping("/coaches")
    public ResponseEntity<Coach> createCoach(@Valid @RequestBody Coach coach) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adminService.createCoach(coach));
    }

    /**
     * 删除学员账号
     */
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        adminService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}