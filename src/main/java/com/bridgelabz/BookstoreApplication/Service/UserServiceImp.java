package com.bridgelabz.BookstoreApplication.Service;

import com.bridgelabz.BookstoreApplication.Dto.Login;
import com.bridgelabz.BookstoreApplication.Dto.UserDto;
import com.bridgelabz.BookstoreApplication.Dto.Verification;
import com.bridgelabz.BookstoreApplication.Exception.BookStoreCustomException;
import com.bridgelabz.BookstoreApplication.Model.UserData;
import com.bridgelabz.BookstoreApplication.Repository.UserRepository;
import com.bridgelabz.BookstoreApplication.Util.EmailService;
import com.bridgelabz.BookstoreApplication.Util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JWTToken jwtToken;

    @Override
    public List<UserData> getAllData() {
        return userRepository.findAll();
    }

    @Override
    public String register(UserDto userDto) {
        String email=userDto.getEmail();
        System.out.println("the email is "+email);
        String mail=userRepository.findEmail(email);
        System.out.println("the-"+mail+"the int mail is ");
        if(mail!=null){
            return "Enter the Email id ";
        }else {
            UserData userData = new UserData(userDto);
            int generateOtp = (int) ((Math.random() * 9999) % 8998) + 1001;
            userData.setOtp(generateOtp);
            userRepository.save(userData);
            System.out.println(email);
            emailService.sendEmail(userData.getEmail(),"The data added successfully ", "hi  .." + userData.getFirstName()+userData.getLastName()+ "\n your data added successfully " + "\n your otp is  <- " + generateOtp + " ->");
            return "otp generated successfully........ ";
        }
    }

    @Override
    public String verification(Verification verification) {
        String email=verification.getEmail();
        int id=userRepository.findByEmail(email);
        System.out.println("the id "+id);
        Optional<UserData> data=userRepository.findById(id);
        if(verification.getOtp()==data.get().getOtp()){
            String token=jwtToken.createToken(id);
            data.get().setVerify(true);
            data.get().setToken(token);
            userRepository.save(data.get());
            return "validation done............ " + data.get().getEmail() ;
        }
        else {
            return "validation not done";
        }
    }
    @Override
    public String login(Login login) {
        String email =login.getEmail();
        String password=login.getPassword();
        String verifyPassword=userRepository.getPassword(email);
        if (password.equals(verifyPassword)){
            return "login successfully..... ";
        }
        else{
            return" check the email and password";
        }
        }

    @Override
    public UserData getById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new BookStoreCustomException(" Employee Not found .. wih id: "+ id));

    }

    @Override
    public UserData UpdateUser(int id, UserDto userDto) {
        UserData addressBookData =this.getById(id);
        addressBookData.updateData(userDto);
        return userRepository.save(addressBookData);
    }

    @Override
    public void delete(int id) {
        UserData data=this.getById(id);
        userRepository.delete(data);
    }

    @Override
    public UserData getdataByToken(String token) {
        int id= jwtToken.decodeToken(token);
        System.out.println(id+"id -------------");
        return userRepository.findById(id).orElseThrow(() -> new BookStoreCustomException("Employee Not found :- "+id));
    }

    @Override
    public String deletedataByToken(String token) {
        int id= jwtToken.decodeToken(token);
        UserData userData =this.getById(id);
        userRepository.delete(userData);
        return "The user data is deleted";
    }

    @Override
    public String forgotPassword(String email) {
        int id = userRepository.findByEmail(email);
        UserData userData = userRepository.findById(id).orElseThrow(() -> new BookStoreCustomException(" Employee Not found .. wih id: " + id));
        Optional<UserData> data = userRepository.findById(id);
        if (id <= 0) {
            return "The mail is not Registered..............";
        } else {
            int generateOtp = (int) ((Math.random() * 9999) % 8998) + 1001;
            data.get().setOtp(generateOtp);
            data.get().setVerifyOtp(false);
            userRepository.save(data.get());
            emailService.sendEmail(userData.getEmail(), "The data added successfully ", "hi...." + userData.getFirstName()+userData.getLastName() + "\n your data added successfully " + "\n your otp is  <- " + generateOtp + " ->");
            return "The forgot password otp sent " ;
        }
    }

    @Override
    public String resetpassword(String email, String password, int otp) {
        String mail = userRepository.findEmail(email);
        if (mail==null){
            return "email is not present";
        }
        else{
            int id =userRepository.findByEmail(email);
            long actualOtp= (int) userRepository.findOtpByEmail(email);
            System.out.println(actualOtp+"otp");
            Optional<UserData> data = userRepository.findById(id);
            if ((data.isPresent() && (otp==actualOtp))) {
                data.get().setPassword(password);
                data.get().setVerifyOtp(true);
                userRepository.save(data.get());
                return "The Password reset successfully..........";
            } else {
                return "password validation not completed ";
            }
        }
    }
}

