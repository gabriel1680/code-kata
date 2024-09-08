import re

def have_more_than_8_len(password: str) -> bool:
    return len(password) >= 8

def contains(pattern: str, text: str) -> bool:
    return re.search(pattern, text) is not None

def have_lower_letter(password: str) -> bool:
    return contains("[a-z]", password)

def have_upper_letter(password: str) -> bool:
    return contains("[A-Z]", password)

def have_number(password: str) -> bool:
    return contains("[0-9]", password)

def have_underscore(password: str) -> bool:
    return contains("[_]", password)

validators = [
    have_more_than_8_len,
    have_lower_letter,
    have_upper_letter,
    have_number,
    have_underscore
]

def validate_password(password: str) -> bool:
    for rule in validators:
        if rule(password) == False:
            return False
    return True

