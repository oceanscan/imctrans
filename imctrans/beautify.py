def beautify(text, space_count=2):
    """Cleanup and indent source file."""
    indent = 0
    blank = False
    list0 = []

    # Remove extra empty lines and indent.
    lines = text.splitlines()
    for line in lines:
        strip = line.strip()
        if len(strip) == 0:
            if blank:
                continue
            else:
                blank = True
                list0.append('')
                continue
        else:
            blank = False

        if strip == '};' and len(list0[-1]) == 0:
            list0.pop()

        if strip.startswith('}') and strip.endswith('{'):
            indent -= space_count
            list0.append(' ' * indent + strip)
            indent += space_count
        elif strip.endswith('{'):
            list0.append(' ' * indent + strip)
            indent += space_count
        elif strip.endswith('}') or strip.endswith('};'):
            indent -= space_count
            list0.append(' ' * indent + strip)
        elif strip == 'public:' or strip == 'protected:' or strip == 'public:':
            list0.append(' ' * (indent - 2) + strip)
        else:
            list0.append(' ' * indent + strip)

    # Remove empty lines between blocks.
    list1 = []
    for line in list0:
        strip = line.strip()
        if (strip == '}' or strip == '};') and len(list1[-1]) == 0:
            list1.pop()
        list1.append(line)

    # Remove extra empty lines at EOF.
    while len(list1[-1]) == 0:
        list1.pop()

    return '\n'.join(list1) + '\n'
