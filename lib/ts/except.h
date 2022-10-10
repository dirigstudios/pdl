/* librería del Compilador Borland C++ Builder 4 */
/* Si el compilador usado para compilar el TAD TS no dispone 
   de este fichero de cabecera, deberá usarse éste */
/*  except.h

    Definitions for exception handling

*/

/*
 *      C/C++ Run Time Library - Version 9.0
 *
 *      Copyright (c) 1992, 1998 by Borland International
 *      All Rights Reserved.
 *
 */
/* $Revision:   9.13  $ */


#ifndef __EXCEPT_H
#define __EXCEPT_H

#ifndef __cplusplus
#  error Must use C++ for except.h
#endif

#if !defined(___STDLIB_H)
#  include <stdlib.h>
#endif

#if !defined(__STD_EXCEPTION)
#  include <exception>
#endif

// forward declare string
namespace std {

template<class charT> struct _RWSTDExportTemplate char_traits;
template<class T> class _RWSTDExportTemplate allocator;

template<class charT, class traits, class Allocator> class _RWSTDExportTemplate
basic_string;

typedef basic_string<char, char_traits<char>, allocator<char> > string;

}

using std::string;

#if !defined(RC_INVOKED)

#pragma pack(push, 1)
#pragma option push -Vo-     // set standard C++ options

#if defined(__STDC__)
#pragma warn -nak
#endif

#endif  /* !RC_INVOKED */


typedef void (_RTLENTRY *terminate_handler)();
typedef void (_RTLENTRY *unexpected_handler)();

#ifndef __STDC__
// For backwards compatibility ...
typedef unexpected_handler unexpected_function;
typedef terminate_handler terminate_function;
#pragma obsolete terminate_function
#pragma obsolete unexpected_function
#endif // !__STDC__

terminate_handler  _RTLENTRY set_terminate(terminate_handler);
unexpected_handler _RTLENTRY set_unexpected(unexpected_handler);

void  _RTLENTRY terminate();
void  _RTLENTRY unexpected();


extern  char *      _RTLENTRY __ThrowFileName();
extern  unsigned    _RTLENTRY __ThrowLineNumber();
extern  char *      _RTLENTRY __ThrowExceptionName();

#define  __throwFileName      __ThrowFileName()
#define  __throwLineNumber    __ThrowLineNumber()
#define  __throwExceptionName __ThrowExceptionName()

class _EXPCLASS xmsg : public std::exception
{
public:
    xmsg(const std::string &msg);
    xmsg(const xmsg &);
    virtual ~xmsg() throw();
    xmsg & operator=(const xmsg &);

    virtual const char * what() const throw();
    const std::string & why() const;
    void                raise() throw(xmsg);

private:
    std::string *str;
};

inline const std::string & xmsg::why() const
{
    return *str;
};

/* The xalloc class is here for backwards compatibility ONLY!  Operator new
   will not throw one of these anymore.  Operator new now throws a bad_alloc
   instead.
*/

class _EXPCLASS xalloc : public xmsg
{
public:
    xalloc(const std::string &msg, _SIZE_T size);

    _SIZE_T requested() const;
    void    raise() throw(xalloc);

private:
    _SIZE_T siz;
};


inline xalloc::xalloc(const std::string &msg, _SIZE_T size)
	: xmsg(msg), siz(size)
{
}

inline _SIZE_T xalloc::requested() const
{
    return siz;
}

#pragma obsolete xalloc
#pragma obsolete xmsg

#if !defined(RC_INVOKED)

#if defined(__STDC__)
#pragma warn .nak
#endif

#pragma option pop      // restore user C++ options
/* restore default packing */
#pragma pack(pop)

#endif  /* !RC_INVOKED */


#endif  // __EXCEPT_H
