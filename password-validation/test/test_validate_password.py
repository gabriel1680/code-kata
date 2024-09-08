from src.validate_password import validate_password

def test_password_should_not_contains_only_lower_case_letters():
    password = "xxxxxxxx"
    assert False is validate_password(password)
  
def test_password_should_not_contains_only_upper_case_letters():
    password = "XXXXXXXX"
    assert False is validate_password(password)
  
def test_password_should_have_at_least_one_number():
    password = "xxxXXXxx"
    assert False is validate_password(password)

def test_password_should_have_at_least_one_letter():
    password = "111111111"
    assert False is validate_password(password)

def test_password_should_have_at_least_one_underscore():
    password = "Xx1xxxxx"
    assert False is validate_password(password)

def test_password_should_not_have_less_than_8_char():
    password = "Xxx4xx_"
    assert False is validate_password(password)

def test_password_should_be_ok():
    password = "Password4_8"
    assert validate_password(password)

