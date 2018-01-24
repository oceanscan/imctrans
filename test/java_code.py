from unittest import TestCase

import imctrans.java.code
import imctrans.cpp.utils


class TestJavaCode(TestCase):
    def test_underscore_to_camelcase(self):
        self.assertEqual(imctrans.java.code.under_to_camel('x'), 'x')
        self.assertEqual(imctrans.cpp.utils.abbrev_to_var('x'), 'x')
        self.assertEqual(imctrans.java.code.under_to_camel('get_x'), 'getX')
        self.assertEqual(imctrans.cpp.utils.abbrev_to_var('get_x'), 'getX')

        self.assertEqual(imctrans.java.code.under_to_camel('get_one_two'), 'getOneTwo')
        self.assertEqual(imctrans.cpp.utils.abbrev_to_var('get_one_two'), 'getOneTwo')



    def test_camelcase_to_underscore(self):
        self.assertEqual(imctrans.java.code.camel_to_under('OP'), 'op')
        self.assertEqual(imctrans.java.code.camel_to_under('ZUnits'), 'z_units')
        self.assertEqual(imctrans.java.code.camel_to_under('SpeedUnits'), 'speed_units')
