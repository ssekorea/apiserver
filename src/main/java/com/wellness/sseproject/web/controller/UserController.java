package com.wellness.sseproject.web.controller;

import com.wellness.sseproject.application.*;
import com.wellness.sseproject.domain.*;
import com.wellness.sseproject.util.JwtTokenUtil;
import com.wellness.sseproject.web.controller.dto.*;
import com.wellness.sseproject.web.controller.message.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserApplicationService userApplicationService;

    @Autowired
    UserQueryService userQueryService;

    @Autowired
    CourseApplicationService courseApplicationService;

    @Autowired
    CourseQueryService courseQueryService;

    @Autowired
    OrderApplicationService orderApplicationService;

    @Autowired
    OrderQueryService orderQueryService;

    @Autowired
    QuestionBoardApplicationService questionBoardApplicationService;

    @Autowired
    QuestionBoardQueryService questionBoardQueryService;

    @Autowired
    ProductQueryService productQueryService;

    @Autowired
    ProductApplicationService productApplicationService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    PasswordEncoder encoder;


    // Admin and User
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{userId}")
    public ResponseEntity<ApiResponseMessage> getUserById(@PathVariable String userId) {

//        if (!authenticate(userId)){
////            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Forbidden", HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
////        }
        UserInfoDTO userInfoDTO = userQueryService.getUserInfoDTOById(userId);
        if (userInfoDTO == null) {
            ApiResponseMessage apiResponseMessage = new ErrorResponseMessage("Invalid User id");
            apiResponseMessage.setStatusCode(HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(apiResponseMessage, HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new UserResponseMessage(userInfoDTO);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<ApiResponseMessage> modifyUser(@PathVariable String userId, @RequestBody UserModifyDTO userModifyDTO) {

//        if (!authenticate(userId)){
//            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Forbidden", HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
//        }
        if (userQueryService.checkValidId(userId)) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid userId", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        userModifyDTO.setUserId(userId);
        UserInfoDTO userInfoDTO = new UserInfoDTO(userApplicationService.modifyUser(userModifyDTO));
        ApiResponseMessage apiResponseMessage = new UserResponseMessage(userInfoDTO);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId) {

        User user = userQueryService.getUserById(userId);
        if (user == null) {
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Invalid userId", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        userApplicationService.deleteUser(user);
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setHttpStatusCodeOK();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);

    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getUserList(@RequestParam(defaultValue = "0") int startIndex, @RequestParam(defaultValue = "30")int pageCount) {

        List<UserDTO> userDTOList = userQueryService.getUserList(startIndex, pageCount);
        if (userDTOList == null){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("No contents", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        ApiResponseMessage apiResponseMessage = new UserPushListMessage();
        apiResponseMessage.setStatusCode(HttpStatus.OK.toString());
        ((UserPushListMessage) apiResponseMessage).setUserList(userDTOList);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //TEST DONE
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{userId}/courses", method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getUserCourseLectures(@PathVariable String userId) {
        //course
        List<CourseLectureDTO> courseLectureDTOList = courseQueryService.getCourseLecturesByUserId(userId);
        if (courseLectureDTOList == null){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("User doesn't have course", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        ApiResponseMessage apiResponseMessage = new CourseLectureListResponseMessage(courseLectureDTOList);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    // TEST DONE
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{userId}/courses/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getUserCourseLecture(@PathVariable String userId, @PathVariable int courseId) {
        //course
        CourseLectureDTO courseLectureDTOList = courseQueryService.getCourseLectureByUserIdAndCourseId(userId, courseId);
        if (courseLectureDTOList == null){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("No contents", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        ApiResponseMessage apiResponseMessage = new CourseLectureResponseMessage(courseLectureDTOList);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    //Test Complete
    @RequestMapping(value = "/{userId}/courses", method = RequestMethod.POST)
    public ResponseEntity<ApiResponseMessage> registerUserCourse(@PathVariable String userId,
                                                                 @RequestBody CourseRegisterDTO courseRegisterDTO) {

        if (!courseApplicationService.registerCourse(userId, courseRegisterDTO)){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("Course is already full Or User is already course this lecture", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setHttpStatusCodeOK();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/courses/{courseId}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponseMessage> deleteUserCourse(@PathVariable String userId, @PathVariable int courseId) {
        Course course = courseQueryService.getCourseByUserIdAndCourseId(userId, courseId);
        if (course == null){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("there is no course", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        if (!userId.equals(course.getUserId())){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("You don't have access", HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
        }

        courseApplicationService.deleteCourseByCourseId(courseId);
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setHttpStatusCodeOK();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);

    }

    @RequestMapping(value = "/{userId}/orders", method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getUserOrders(@PathVariable String userId, @RequestParam(defaultValue = "0") int startIndex, @RequestParam(defaultValue = "10") int count){
        List<OrderProductDTO> orderProductList = orderQueryService.getOrderListByUserIdAndPage(userId, startIndex, count);
        if (orderProductList == null){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("No Order", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new OrderProductListResponseMessage(orderProductList);

        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/orders", method = RequestMethod.POST)
    public ResponseEntity<ApiResponseMessage> registerUserOrder(@PathVariable String userId, @RequestBody OrderRegisterDTO orderRegisterDTO){
        orderRegisterDTO.setUserId(userId);
        //재고 검사
        if (!productQueryService.isStockAvailable(orderRegisterDTO.getProductId(), orderRegisterDTO.getOrderNum())){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("There is No stock", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        Order order = orderApplicationService.registerOrder(orderRegisterDTO);
        ApiResponseMessage apiResponseMessage = new OrderResponseMessage(order);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/boards", method = RequestMethod.POST)
    public ResponseEntity<ApiResponseMessage> registerQuestion(@PathVariable String userId, @RequestBody QuestionBoardRegisterDTO questionBoardRegisterDTO){
        questionBoardRegisterDTO.setUserId(userId);
        QuestionBoard questionBoard = questionBoardApplicationService.registerQuestionBoard(questionBoardRegisterDTO);

        ApiResponseMessage apiResponseMessage = new QuestionBoardResponseMessage(questionBoard);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/boards", method = RequestMethod.GET)
    public ResponseEntity<ApiResponseMessage> getUserQuestionBoardList(@PathVariable String userId){
        List<QuestionBoard> questionBoardList = questionBoardQueryService.getQuestionBoardsByUserId(userId);
        if (questionBoardList == null){
            return new ResponseEntity<>(ErrorResponseMessageFactory.createErrorResponseMessageFactory("No question", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
        ApiResponseMessage apiResponseMessage = new QuestionBoardListResponseMessage(questionBoardList);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    // permitAll()
    @RequestMapping(method = RequestMethod.POST, value = "/{userId}")
    public ResponseEntity<ApiResponseMessage> registerUser(@PathVariable String userId, @RequestBody UserInfoDTO userInfoDTO) {

        // registerType에 따른 .... DTO 에서 넘어온 거는 유저타입으 무시 general로..
        if (!userQueryService.checkValidId(userId)) {
            return new ResponseEntity<>(ErrorResponseMessageFactory
                    .createErrorResponseMessageFactory("User Id is already existed", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        userInfoDTO.setUserId(userId);
        if (userInfoDTO.getPassword() != null){
            userInfoDTO.setPassword(encoder.encode(userInfoDTO.getPassword()));
        }
        //Hard Coded
        userInfoDTO.setUserType("USER");
        userApplicationService.registerUser(userInfoDTO);

        String token = jwtTokenUtil.generateToken(userInfoDTO);
        userInfoDTO.setToken(token);

        ApiResponseMessage apiResponseMessage = new UserResponseMessage(userInfoDTO);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    private boolean authenticate(String userId) {
        UserInfoDTO userInfoDTO;
        try{
            userInfoDTO = (UserInfoDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (ClassCastException e){
            return false;
        }

        if (userInfoDTO.getUserId().equals(userId)){
            return true;
        }

        if (userInfoDTO.getUserType().equals("ADMIN")){
            return true;
        }
        return false;
    }


}
