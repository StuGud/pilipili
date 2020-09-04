package com.seu.pilipili.service.impl;

import com.seu.pilipili.entity.User;
import com.seu.pilipili.repo.UserRepo;
import com.seu.pilipili.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Value("${pilipili.profilePath.WIN}")
    String WINDOWS_PROFILES_PATH;
    @Value("${pilipili.profilePath.MAC}")
    String MAC_PROFILES_PATH;

    final
    UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User register(User user) {
        return userRepo.save(user);
    }

    @Override
    public User login(String username,String password) {
        return userRepo.findByUsernameAndPassword(username,password);
    }

    @Override
    public boolean existsUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public User modify(User user) {
        User save = userRepo.findById(user.getId()).get();
        if (user.getUsername()!=null){
            save.setUsername(user.getUsername());
        }
        if(user.getPassword()!=null){
            save.setPassword(user.getPassword());
        }
        if (user.getMail()!=null){
            save.setMail(user.getMail());
        }
        if(user.getLevel()!=null){
            save.setLevel(user.getLevel());
        }
        if (user.getProfileURL()!=null){
            save.setProfileURL(user.getProfileURL());
        }
        return userRepo.save(save);
    }

    @Override
    public User modifyApprovalNum(long userId,boolean change) {
        User user=userRepo.findById(userId).get();
        int approvalNum= user.getApprovalNum();
        if (change){
            user.setApprovalNum(approvalNum+1);
        }else{
            user.setApprovalNum(approvalNum-1);
        }
        return userRepo.save(user);
    }

    @Override
    public User setProfile(long userId, MultipartFile newProfile) {
        User user = userRepo.findById(userId).get();
        return null;
    }

    @Override
    public File getProfile(long userId) {
        String OSName = System.getProperty("os.name");
        String profilesPath = OSName.toLowerCase().startsWith("win") ? WINDOWS_PROFILES_PATH
                : MAC_PROFILES_PATH;
        Optional<User> user=userRepo.findById(userId);
        if(user.isPresent()){
            String imageDirectory=user.get().getProfileURL();
            return new File(profilesPath+imageDirectory);
        }
        return null;
    }

    @Override
    public User getDetails(long userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setPassword("你猜");
            return user;
        }
        return null;
    }
}
