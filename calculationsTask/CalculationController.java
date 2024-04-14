import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculationController {

    @Autowired
    private CalculationRepository calculationRepository;

    @PostMapping("/api/calc")
    public CalculationResult createCalculation(@RequestBody CalculationRequest request) {
        int number1 = request.getNumber1();
        int number2 = request.getNumber2();
        String operation = request.getOperation();

        int result;
        switch (operation) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                result = number1 / number2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }

        Calculations calculation = new Calculation(number1, number2, operation);
        calculationRepository.save(calculation);

        return new CalculationResult(result);
    }
    @GetMapping("/api/calculations")
    public List<Calculation> getAllCalculations() {
        return calculationRepository.findAll();
    }
}
