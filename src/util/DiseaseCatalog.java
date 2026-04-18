package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class DiseaseCatalog {
    private static final List<String> DISEASES = Arrays.asList(
            "Hypertension",
            "Diabetes",
            "Asthma",
            "Migraine",
            "Influenza",
            "Common Cold",
            "Pneumonia",
            "Bronchitis",
            "Tuberculosis",
            "COVID-19",
            "Malaria",
            "Dengue Fever",
            "Typhoid",
            "Cholera",
            "Hepatitis A",
            "Hepatitis B",
            "Hepatitis C",
            "Jaundice",
            "Anemia",
            "Arthritis",
            "Osteoporosis",
            "Back Pain",
            "Sciatica",
            "Gastritis",
            "Peptic Ulcer",
            "Acid Reflux",
            "Irritable Bowel Syndrome",
            "Constipation",
            "Diarrhea",
            "Food Poisoning",
            "Appendicitis",
            "Kidney Stones",
            "Urinary Tract Infection",
            "Chronic Kidney Disease",
            "Liver Cirrhosis",
            "Fatty Liver Disease",
            "Pancreatitis",
            "Hypothyroidism",
            "Hyperthyroidism",
            "Goiter",
            "Depression",
            "Anxiety Disorder",
            "Bipolar Disorder",
            "Insomnia",
            "Epilepsy",
            "Parkinson's Disease",
            "Alzheimer's Disease",
            "Stroke",
            "Heart Attack",
            "Coronary Artery Disease",
            "Arrhythmia",
            "Heart Failure",
            "Varicose Veins",
            "Sinusitis",
            "Allergic Rhinitis",
            "Eczema",
            "Psoriasis",
            "Acne",
            "Dermatitis",
            "Fungal Infection",
            "Ringworm",
            "Chickenpox",
            "Measles",
            "Mumps",
            "Polio",
            "Rubella",
            "Whooping Cough",
            "Tonsillitis",
            "Ear Infection",
            "Conjunctivitis",
            "Glaucoma",
            "Cataract",
            "Dry Eye Syndrome",
            "Obesity",
            "Malnutrition",
            "Gout",
            "Rheumatoid Arthritis",
            "Lupus",
            "Leukemia",
            "Lymphoma",
            "Breast Cancer",
            "Lung Cancer",
            "Colon Cancer",
            "Prostate Cancer",
            "Skin Cancer",
            "Cervical Cancer",
            "Endometriosis",
            "PCOS",
            "Menstrual Disorder",
            "Pregnancy-Induced Hypertension",
            "Gestational Diabetes",
            "Infertility",
            "Hemorrhoids",
            "Hernia",
            "Sleep Apnea",
            "COPD",
            "Fibromyalgia",
            "Vertigo",
            "Dehydration",
            "Sepsis");
    private static final Map<String, String> DISEASE_SPECIALIZATIONS = createDiseaseSpecializations();

    private DiseaseCatalog() {
    }

    public static List<String> getStartupDiseases() {
        List<String> shuffledDiseases = new ArrayList<>(DISEASES);
        Collections.shuffle(shuffledDiseases);
        return shuffledDiseases;
    }

    public static List<String> getDoctorSpecializations() {
        Set<String> specializations = new LinkedHashSet<>();
        for (String disease : DISEASES) {
            specializations.add(getSpecializationForDisease(disease));
        }
        return new ArrayList<>(specializations);
    }

    public static String getSpecializationForDisease(String disease) {
        return DISEASE_SPECIALIZATIONS.getOrDefault(disease, "General Medicine");
    }

    private static Map<String, String> createDiseaseSpecializations() {
        Map<String, String> specializationMap = new LinkedHashMap<>();

        addSpecialization(specializationMap, "Cardiology",
                "Hypertension", "Heart Attack", "Coronary Artery Disease", "Arrhythmia", "Heart Failure",
                "Pregnancy-Induced Hypertension");
        addSpecialization(specializationMap, "Endocrinology",
                "Diabetes", "Hypothyroidism", "Hyperthyroidism", "Goiter", "Obesity", "Gestational Diabetes");
        addSpecialization(specializationMap, "Pulmonology",
                "Asthma", "Pneumonia", "Bronchitis", "Tuberculosis", "COVID-19", "Sleep Apnea", "COPD");
        addSpecialization(specializationMap, "Neurology",
                "Migraine", "Epilepsy", "Parkinson's Disease", "Alzheimer's Disease", "Stroke", "Vertigo");
        addSpecialization(specializationMap, "Infectious Disease",
                "Influenza", "Common Cold", "Malaria", "Dengue Fever", "Typhoid", "Cholera", "Chickenpox",
                "Measles", "Mumps", "Polio", "Rubella", "Whooping Cough", "Sepsis");
        addSpecialization(specializationMap, "Hepatology",
                "Hepatitis A", "Hepatitis B", "Hepatitis C", "Jaundice", "Liver Cirrhosis", "Fatty Liver Disease");
        addSpecialization(specializationMap, "Hematology",
                "Anemia", "Leukemia", "Lymphoma");
        addSpecialization(specializationMap, "Rheumatology",
                "Arthritis", "Osteoporosis", "Gout", "Rheumatoid Arthritis", "Lupus", "Fibromyalgia");
        addSpecialization(specializationMap, "Orthopedics",
                "Back Pain", "Sciatica");
        addSpecialization(specializationMap, "Gastroenterology",
                "Gastritis", "Peptic Ulcer", "Acid Reflux", "Irritable Bowel Syndrome", "Constipation", "Diarrhea",
                "Food Poisoning", "Pancreatitis", "Hemorrhoids");
        addSpecialization(specializationMap, "General Surgery",
                "Appendicitis", "Hernia");
        addSpecialization(specializationMap, "Nephrology",
                "Kidney Stones", "Chronic Kidney Disease", "Dehydration");
        addSpecialization(specializationMap, "Urology",
                "Urinary Tract Infection", "Prostate Cancer");
        addSpecialization(specializationMap, "ENT",
                "Sinusitis", "Allergic Rhinitis", "Tonsillitis", "Ear Infection");
        addSpecialization(specializationMap, "Dermatology",
                "Eczema", "Psoriasis", "Acne", "Dermatitis", "Fungal Infection", "Ringworm", "Skin Cancer");
        addSpecialization(specializationMap, "Ophthalmology",
                "Conjunctivitis", "Glaucoma", "Cataract", "Dry Eye Syndrome");
        addSpecialization(specializationMap, "Oncology",
                "Breast Cancer", "Lung Cancer", "Colon Cancer", "Cervical Cancer");
        addSpecialization(specializationMap, "Psychiatry",
                "Depression", "Anxiety Disorder", "Bipolar Disorder", "Insomnia");
        addSpecialization(specializationMap, "Gynecology",
                "Endometriosis", "PCOS", "Menstrual Disorder", "Infertility");
        addSpecialization(specializationMap, "Nutrition",
                "Malnutrition");
        addSpecialization(specializationMap, "Vascular Medicine",
                "Varicose Veins");

        return specializationMap;
    }

    private static void addSpecialization(Map<String, String> specializationMap, String specialization,
            String... diseases) {
        for (String disease : diseases) {
            specializationMap.put(disease, specialization);
        }
    }
}
