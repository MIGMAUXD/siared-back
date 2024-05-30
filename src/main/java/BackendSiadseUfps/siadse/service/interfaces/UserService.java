package BackendSiadseUfps.siadse.service.interfaces;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BackendSiadseUfps.siadse.dto.LoginDTO;
import BackendSiadseUfps.siadse.dto.RoleDTO;
import BackendSiadseUfps.siadse.dto.UserDTO;
import BackendSiadseUfps.siadse.entity.Role;
import BackendSiadseUfps.siadse.entity.User;
import BackendSiadseUfps.siadse.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(UserDTO userDTO) {
        if (userDTO.getName() == null) {
            throw new RuntimeException("El campo 'username' es requerido.");
        } 
    	User user = new User();
      user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());  // Consider using encryption in a real scenario
        user.setCodigoUniversidad(userDTO.getCodigoUniversidad());
        user.setSemestreActual(userDTO.getSemestreActual());
        user.setEdad(userDTO.getEdad());
        user.setDireccionResidencia(userDTO.getDireccionResidencia());
        user.setCelular(userDTO.getCelular());

        if (userDTO.getRole() != null) {
            RoleDTO roleDTO = userDTO.getRole();
            Role role = new Role();
            role.setId(roleDTO.getId());
            role.setName(roleDTO.getName());  // Asegúrate de que el nombre del rol no sea nulo
            user.setRole(role);
        } else {
            throw new RuntimeException("Información del rol es requerida.");
        }
        
        user.setLoggedIn(false);
        return userRepository.save(user);


    }

    public boolean loginUser(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user != null && user.getPassword().equals(loginDTO.getPassword())) {
            user.setLoggedIn(true); // Marcar al usuario como autenticado
            userRepository.save(user);
            return true;
        }
        return false;
    }
    public void logoutUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setLoggedIn(false); // Marcar al usuario como no autenticado
        userRepository.save(user);
    }

}
