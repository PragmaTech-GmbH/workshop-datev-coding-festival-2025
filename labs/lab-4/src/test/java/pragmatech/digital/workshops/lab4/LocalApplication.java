package pragmatech.digital.workshops.lab4;

import org.springframework.boot.SpringApplication;

public class LocalApplication {
  public static void main(String[] args) {
    SpringApplication.from(Lab4Application::main).with(LocalDevTestcontainerConfig.class).run(args);
  }
}
