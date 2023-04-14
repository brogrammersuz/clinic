package uz.brogrammers.clinic.department.enums;

public enum DepartmentName {

    LABORATORY("Лабаратория"),
    ULTRASOUND("Узи"),
    X_REY("Рентген"),
    MRT("МРТ"),
    MCKT("МСКТ"),
    NEUROLOGY("Неврология"),
    UROLOGY("Уралогия"),
    GYNECOLOGY("Гинекология"),
    SURGERY("Хирургия"),
    TRAUMATOLOGY("Траматология"),
    LOR("Лор"),
    CARDIOLOGY("Кардиоголия"),
    THERAPY("Терапия"),
    ENDOCRINOLOGY("Ендакринология"),
    GASTROENTEROLOGY("Гастроэнтерология"),
    PHYSIOLOGY("Физотерапия"),
    REGISTRATION("Регистратура");

    private final String name;

    DepartmentName(final String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}