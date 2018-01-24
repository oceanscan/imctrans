from unittest import TestCase

import imctrans.beautify

class TestIndent(TestCase):
    def test_function(self):
        input = 'public PolygonVertex() {\n' \
                'super(ID_STATIC);\n' \
                '}'

        expected = 'public PolygonVertex() {\n' \
                   '    super(ID_STATIC);\n' \
                   '}\n'

        actual = imctrans.beautify.beautify(input, 4)
        self.assertEqual(actual, expected)

    def test_if_within_function(self):
        input = 'public PolygonVertex() {\n' \
                'if (cond) {\n' \
                'super(ID_STATIC);\n' \
                '}\n' \
                '}'

        expected = 'public PolygonVertex() {\n' \
                   '    if (cond) {\n' \
                   '        super(ID_STATIC);\n' \
                   '    }\n' \
                   '}\n'

        actual = imctrans.beautify.beautify(input, 4)
        self.assertEqual(actual, expected)

    def test_if_else_within_function(self):
            input = 'public PolygonVertex() {\n' \
                    'if (cond) {\n' \
                    'super(ID_STATIC);\n' \
                    '} else {\n' \
                    'call()\n' \
                    '}\n' \
                    '}'

            expected = 'public PolygonVertex() {\n' \
                       '    if (cond) {\n' \
                       '        super(ID_STATIC);\n' \
                       '    } else {\n' \
                       '        call()\n' \
                       '    }\n' \
                       '}\n'

            actual = imctrans.beautify.beautify(input, 4)
            self.assertEqual(actual, expected)

    def test_nl_between_close_braces(self):
            input = '{\n' \
                    '{\n' \
                    '}\n' \
                    '\n' \
                    '\n' \
                    '}\n'

            expected = '{\n' \
                       '    {\n' \
                       '    }\n' \
                       '}\n'

            actual = imctrans.beautify.beautify(input, 4)
            self.assertEqual(actual, expected)

