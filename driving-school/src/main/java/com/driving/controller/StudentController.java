// src/main/java/com/driving/controller/StudentController.java
package com.driving.controller;

@RestController
@RequestMapping("/api/student")
@PreAuthorize("hasRole('STUDENT')")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 获取学员个人信息
     */
    @GetMapping("/profile")
    public ResponseEntity<Student> getProfile(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(studentService.getProfile(token));
    }

    /**
     * 更新联系方式
     */
    @PutMapping("/contact")
    public ResponseEntity<Student> updateContact(
            @RequestParam String newPhone,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(studentService.updateContact(token, newPhone));
    }
}