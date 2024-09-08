import re
from typing import Callable

def have_more_than(n: int) -> Callable[[str], bool]:
    return lambda password : len(password) >= n

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

all_rules = [
    have_more_than(8),
    have_lower_letter,
    have_upper_letter,
    have_number,
    have_underscore
]

simple_rules = [
    have_more_than(6),
    have_number,
    have_lower_letter,
    have_upper_letter,
]

mixed_rules = [
    have_more_than(16),
    have_lower_letter,
    have_upper_letter,
    have_underscore,
]

def validate_password(password: str, rules: list[Callable[[str], bool]] = all_rules) -> bool:
    for rule in rules:
        if rule(password) == False:
            return False
    return True

